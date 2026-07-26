# Diseño Arquitectónico Avanzado — Julio's Burger (SaaS Ready)

Este documento técnico rige la arquitectura oficial del proyecto en su **versión final**. Emplea Clean Architecture, Domain-Driven Design (DDD) y una arquitectura orientada a eventos para crear una plataforma robusta y escalable (SaaS Ready).

---

## 1. Visión y Objetivo del Producto
El sistema se define estrictamente como un **Asistente Inteligente para Gestión de Pedidos por WhatsApp**.
Su objetivo **NO** es reemplazar al cajero ni operar de forma autónoma. Su misión es automatizar la conversación y estructurar el pedido, de modo que el personal humano únicamente se dedique a revisar, validar y aceptar el pedido. La Inteligencia Artificial (IA) actúa como un asistente para el negocio, pero la autoridad final siempre recae en el cajero humano.

---

## 2. Principios Arquitectónicos (Architectural Principles)
Para salvaguardar la integridad de la plataforma, todas las implementaciones deberán regirse por las siguientes reglas absolutas:
- **La IA nunca crea pedidos definitivos.** Todo pedido requiere validación humana.
- **Todo pedido requiere confirmación explícita del cliente.**
- **La IA nunca modifica directamente la base de datos.**
- **Todas las reglas del negocio pertenecen al dominio (no a los prompts de la IA).**
- **Toda configuración operativa debe ser dinámica (gestionable desde Android).**
- **Todo aprendizaje del sistema debe provenir de una corrección humana (Aprendizaje Supervisado).**
- **Customer Memory solo se utiliza para personalizar la conversación, nunca para alterar pedidos automáticamente.**
- **Business Vocabulary tiene prioridad absoluta sobre la IA.**
- **El sistema debe continuar funcionando aunque cambie el proveedor de IA.**

---

## 3. Prioridad de Resolución de Decisiones (Decision Resolution Priority)
Para interpretar cualquier mensaje del cliente, el sistema seguirá estrictamente este orden de resolución:

1. **Business Vocabulary:** ¿El usuario usó una frase o modismo local previamente registrado?
2. **Reglas del Dominio:** ¿La acción viola alguna regla de negocio (ej. agregar un producto fuera de stock)?
3. **Customer Memory:** ¿Hay contexto histórico de alta confianza aplicable a esta solicitud?
4. **Modelo de IA (LLM):** Únicamente si los tres pasos anteriores no resolvieron la intención.

**Justificación Técnica:** Este orden garantiza que la IA **NO** sea la primera fuente de decisión. Esto reduce drásticamente los costos operativos (menos llamadas a APIs externas), aumenta la precisión a casi 100% para interacciones repetitivas, mantiene la consistencia del servicio y evita "alucinaciones" o interpretaciones innecesarias del modelo.

---

## 4. Flujo Oficial del Pedido y Conversación
Este es el único flujo válido para estructurar e inyectar pedidos a la cocina, reflejando el orden de resolución:

**Flujo Conversacional de Procesamiento:**
`Cliente` → `Conversation Engine` → `Business Vocabulary` → `Intent Detection` → `Entity Extraction` → `Reglas del Dominio` → `Customer Memory (si aplica)` → `Modelo de IA (Solo si pasos previos no son suficientes)` → `Respuesta`.

**Flujo Transaccional:**
1. Conversación y Estructuración (Pasos previos).
2. Se genera un pedido preliminar (Borrador).
3. Se genera un resumen conversacional.
4. **Cliente confirma** explícitamente.
5. El pedido entra a la **Cola de Revisión (`PENDING_CASHIER_REVIEW`)**.
6. **El cajero revisa**, y si es necesario, corrige.
7. **El cajero acepta el pedido.**
8. El pedido entra a preparación (`COOKING`).
9. Pedido listo (`READY`).
10. Entrega al cliente (`DELIVERED`).

---

## 5. Cola de Revisión (Pending Cashier Review)
Módulo en la App Android que representa el control humano.
- Desde esta vista, el cajero puede: Revisar el pedido, editar productos/cantidades, editar modificadores, cambiar dirección o método de pago, modificar observaciones, confirmar disponibilidad, aceptar o cancelar el pedido.
- El pedido solo avanza a la cocina cuando es aceptado manualmente.

---

## 6. Business Vocabulary (Aprendizaje Supervisado)
Representa el lenguaje propio del restaurante. El sistema evoluciona mediante Aprendizaje Supervisado (el cajero corrige la orden y la guarda como aprendizaje), sustituyendo cualquier autoentrenamiento.

- **Diseño del Módulo:**
  - `id`: UUID.
  - `frase`: Patrón de texto (ej. *"con todo"*, *"la especial"*).
  - `intención`: Ej. `ADD_MODIFIERS`.
  - `entidades`: Ej. `[lechuga, jitomate, cebolla, catsup, mayonesa]`.
  - `quién_creó`: ID del empleado/cajero.
  - `fecha_creación`: Timestamp.
  - `última_modificación`: Timestamp.
  - `estado`: Activa / Inactiva.
  - `observaciones`: Notas operativas del registro.
- **Justificación:** El historial y el Soft Delete (estado inactivo) son vitales para revertir aprendizajes erróneos sin perder la trazabilidad de quién y cuándo "enseñó" esa frase al sistema.

---

## 7. Customer Memory (Personalización Segura y Confianza)
Almacena información a largo plazo del cliente pasivamente para la personalización de UX.
- **Campos Estándar:** Teléfono, última compra, frecuencia de compra, dirección favorita, método de pago habitual.
- **Nivel de Confianza (Confidence Score):** Un algoritmo asigna un puntaje (0-100%). Por ejemplo, si el cliente pide "Doble sin cebolla" en 5 ocasiones consecutivas, el producto favorito tiene 95% de confianza.
- **Regla Estricta:** El `Recommendation Engine` usará la Memoria solo si el *Confidence Score* supera un umbral. NUNCA modificará el pedido automáticamente; su uso es estrictamente propositivo (ej. *"¿Te preparo la misma Doble sin cebolla de siempre?"*).

---

## 8. Módulos de Procesamiento e IA Desacoplada
- **Intent Detection y Entity Extraction:** Interfaces genéricas (`LLMProviderInterface`). Extraen `Product`, `Quantity`, `Modifiers`, `Address`.
- Permiten sustituir a OpenAI, Gemini o Claude desde la configuración sin alterar código del negocio.

---

## 9. Conversation Engine y Máquina de Estados
El orquestador de la interacción, independiente del negocio.
- **WhatsAppSession:** Representa la conversación efímera. Expira tras 12 horas. Contiene el estado (`WAITING_ADDRESS`, etc.), orden en borrador y expectativas temporales. Separada totalmente de la *Customer Memory*.

---

## 10. Catálogo, Promociones y Modificadores
- **Catálogo Escalable:** Estructuras `Category` y `Product`. Listo para escalar.
- **Modificadores:** Esquema relacional `Product` → `ModifierGroup` → `ModifierOptions`.
- **Promociones:** Módulo independiente (`Promo_Type`, `Target_Product_id`). Se envían automáticamente con el menú del día y se activan/desactivan desde Android. Los combos se tratan como productos normales.

---

## 11. Disponibilidad Inteligente (Smart Availability)
Reemplaza el inventario complejo por un sistema de *toggles* binarios de disponibilidad.
- Si un ingrediente (ej. cebolla) se marca como agotado desde Android, el Conversation Engine advierte al cliente si lo solicita y negocia si desea continuar sin ese ingrediente o cambiar de producto.

---

## 12. Historial Inmutable de Pedidos (Order Snapshots)
Al pasar a estado `CONFIRMED`, se crea un Snapshot JSON (o copia en `OrderLineHistory`) copiando exactamente el nombre literal, precio y modificadores en ese instante. Cambios futuros en el catálogo (precios/imágenes) no afectarán los reportes financieros históricos.

---

## 13. Sistema de Quejas (Complaint Management)
Módulo que desvía la conversación cuando se detecta el *Intent* de queja.
- **Clasificación Automática:** La IA o el Business Vocabulary pre-clasifica la entrada en: *Queja de pedido*, *Queja del servicio*, *Sugerencia*, *Felicitación*, *Otro*.
- **Impacto:** Esta clasificación acelera la atención desde el dashboard de Android y facilita el análisis estadístico posterior.

---

## 14. Audit Log (Trazabilidad y Auditoría)
Módulo fundamental, inmutable y de solo escritura, para registrar todas las acciones clave del sistema.
- **Acciones Auditadas:** Pedidos (aceptados, modificados, cancelados), cambios en el menú, reglas agregadas al *Business Vocabulary*, cambios de configuración o promociones activadas.
- **Estructura Requerida:**
  - `id`: UUID.
  - `usuario`: ID del administrador o "System".
  - `fecha`: Timestamp.
  - `acción`: Enum (ej. `ORDER_EDITED`, `MENU_UPDATED`).
  - `entidad_afectada`: Nombre o ID del recurso.
  - `valor_anterior`: JSON snapshot.
  - `valor_nuevo`: JSON snapshot.
  - `observaciones`: Justificación (ej. "Corrección de dirección por error de tipeo del cliente").
- **Justificación:** Es imprescindible para auditorías financieras, rastrear responsabilidades en caso de errores en pedidos, y garantizar un entorno corporativo seguro (SaaS Ready).

---

## 15. Estado del Restaurante y Configuración Dinámica
- `RestaurantStatus`: Regula si el bot atiende (`OPEN`) o si lanza plantilla de cierre (`CLOSED`, `STOP_ACCEPTING_ORDERS`).
- `DynamicConfig`: Toda regla dura (horarios, radios de entrega, costos, plantillas de texto) se guarda en BD, es configurable vía Android y rige al sistema entero de manera inmediata.

---

## 16. Roadmap Actualizado de Implementación

### Fase 1: Dominio y Fundamentos Estrictos
- Modelado Relacional (Catálogo, Modificadores, Enums).
- Construcción de Interfaces IA (`LLMProviderInterface`), `Intent Detection` y `Entity Extraction`.
- Módulo central `Conversation Engine` y estructuración del `Business Vocabulary`.

### Fase 2: Conversación y Control Humano (Core Operativo)
- Integración oficial con WhatsApp Business Cloud API.
- Flujo conversacional (Máquina de Estados) con **Decision Resolution Priority**.
- Módulo `Audit Log` y **Cola de Revisión (Pending Cashier Review)**.

### Fase 3: Ecosistema Android y Operaciones
- App Android (Jetpack Compose). Vista de Cola de Revisión (cajero).
- Panel administrativo de Catálogo, Promociones, Disponibilidad Inteligente y Configuración Dinámica.
- Clasificación y visualización en el Sistema de Quejas (Complaint Management).

### Fase 4: Personalización, Aprendizaje y Analítica
- Integración de la `Customer Memory` y sus niveles de confianza.
- Implementación de `Recommendation Engine` (Sugiriendo órdenes previas).
- Mejoras progresivas del `Business Vocabulary` e implementación de reportes/BI en Android.
