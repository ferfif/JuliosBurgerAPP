# Technology Baseline — Julio's Burger

**Proyecto:** Julio's Burger  
**Documento:** TECHNOLOGY_BASELINE.md  
**Versión:** 1.1.0  
**Estado:** Base oficial actualizada  
**Autoría:** Arquitectura de Software  
**Aprobación requerida:** Arquitecto Principal / Tech Lead  

> Actualización de versión 1.0.0 → 1.1.0 para reflejar el stack tecnológico efectivamente compilado en el proyecto tras la activación de Room/KSP.

---

## 1. Objetivo del documento

Este documento define el **stack tecnológico oficial**, las **políticas de construcción** y los **principios de arquitectura** que regirán el proyecto durante todo su ciclo de vida. Su alcance cubre desde la configuración de Gradle hasta las librerías de terceros permitidas.

Toda modificación futura sobre versiones, dependencias, plugins o configuración de construcción **deberá justificarse técnicamente** y actualizar este documento antes de ejecutarse. Este archivo es la **fuente de verdad** para toma de decisiones tecnológicas y servirá como contrato entre desarrollo, QA y operaciones.

---

## 2. Principios

- **Estabilidad antes que novedades.** Se priorizan versiones probadas en producción sobre lanzamientos recientes sin historial.
- **Versiones LTS cuando sea posible.** Se favorecen líneas de soporte extendido para componentes base.
- **Evitar librerías experimentales.** No se incorporan APIs en Alpha, Beta, RC o SNAPSHOT.
- **Compatibilidad hacia atrás.** Los mínimos de SDK y versiones de herramienta deben respaldar al menos el 95% del parque instalado objetivo.
- **Minimizar deuda técnica.** Se prohíbe introducir dependencias sin un plan de migración y salida.
- **Mantener el dominio independiente.** Ninguna dependencia de infraestructura podrá filtrarse al dominio.
- **Reproducibilidad.** La construcción debe ser determinista y funcional en cualquier ambiente sin intervención manual.
- **Traza auditable.** Cada decisión tecnológica queda registrada en este documento.

---

## 3. Stack tecnológico oficial

### 3.1 Herramientas de construcción

| Componente | Versión oficial | Motivo de la elección | Compatibilidad |
|------------|-----------------|----------------------|----------------|
| **Android Studio** | Jellyfish / 2024.3.1+ | Versión estable actual con soporte AGP 8.6.0 | AGP 8.6.0, Kotlin 1.9.22, Compose 2024.06.00 |
| **Gradle** | 8.11.1 | Versión estable de la línea 8.x, alineada con AGP 8.6.0 | AGP 8.6.0, KSP 1.9.22-1.0.18 |
| **Android Gradle Plugin (AGP)** | 8.6.0 | Versión estable actual, madura y con soporte extendido | Gradle 8.11.1, Kotlin 1.9.22, compileSdk 34 |
| **Kotlin** | 1.9.22 | Versión bundled con AGP 8.6.0, alineada con KSP 1.9.x | AGP 8.6.0, KSP 1.9.22-1.0.18 |
| **Java** | 21 | LTS vigente, soportada por AGP 8.6.0 y librerías empresariales | AGP 8.6.0, Room 2.6.1, Hilt 2.51.1 |
| **Desugar JDK** | 2.0.4 | Core library desugaring para compatibilidad con APIs Java 21 en minSdk 24 | AGP 8.6.0, Java 21, minSdk 24 |

### 3.2 Procesadores de anotaciones

| Componente | Versión oficial | Motivo de la elección | Compatibilidad |
|------------|-----------------|----------------------|----------------|
| **KSP** | 1.9.22-1.0.18 | Procesador oficial para Kotlin 1.9.x, reemplaza Kapt en AGP 8.x | Kotlin 1.9.22, Room 2.6.1, Hilt 2.51.1 |
| **Hilt** | 2.51.1 | Inyección de dependencias estándar en Android, soporte nativo KSP | AGP 8.6.0, KSP 1.9.22-1.0.18 |
| **Room** | 2.6.1 | Persistencia oficial Android, arquitectura probada | AGP 8.6.0, KSP 1.9.22-1.0.18 |

### 3.3 Interfaz de usuario

| Componente | Versión oficial | Motivo de la elección | Compatibilidad |
|------------|-----------------|----------------------|----------------|
| **Jetpack Compose** | BOM 2024.06.00 | Bill of Materials oficial, alineación garantizada de versiones | AGP 8.6.0, Kotlin 1.9.22, compileSdk 34 |
| **Navigation Compose** | 2.8.0 (dentro del BOM) | Navegación declarativa oficial para Compose | Compose BOM 2024.06.00 |
| **Material 3** | 1.12.0 | Diseño system moderno, soporte Material You | Compose BOM 2024.06.00 |
| **Core KTX** | 1.13.1 | Extensiones Kotlin para AndroidX, estabilidad probada | AGP 8.6.0, compileSdk 34 |
| **AppCompat** | 1.7.0 | Compatibilidad hacia atrás para componentes legacy | AGP 8.6.0, minSdk 24 |

### 3.4 Concurrencia y red

| Componente | Versión oficial | Motivo de la elección | Compatibilidad |
|------------|-----------------|----------------------|----------------|
| **Coroutines** | 1.8.1 | Estable para Kotlin 1.9.x, ampliamente adoptado | Kotlin 1.9.22, Android |
| **Serialization** | 1.6.3 | Serialización nativa Kotlin, sin reflection | Kotlin 1.9.22, KSP |
| **Retrofit** | 2.11.0 | Cliente HTTP estándar, soporte corrutinas y serialización | OkHttp 4.x, Kotlin 1.9.22 |
| **OkHttp** | 4.12.0 | Motor HTTP subyacente, mantenimiento activo | Retrofit 2.11.0 |

### 3.5 Pruebas

| Componente | Versión oficial | Motivo de la elección | Compatibilidad |
|------------|-----------------|----------------------|----------------|
| **JUnit** | 4.13.2 | Estabilidad absoluta en proyectos Android existentes | AGP 8.6.0, Java 21 |
| **Espresso** | 3.5.1 | Pruebas instrumentadas oficiales | AGP 8.6.0, compileSdk 34 |
| **JUnit AndroidX** | 1.1.5 | Extensión para pruebas instrumentadas | Espresso 3.5.1 |

---

## 4. Política de actualizaciones

- **Nunca actualizar durante una fase activa.** Las actualizaciones de dependencias se ejecutan exclusivamente entre releases o durante ventanas de mantenimiento planificadas.
- **Solo actualizar entre releases.** La ventana permitida es el periodo entre cierre de una versión y apertura de la siguiente.
- **Nunca adoptar Alpha.** Versiones marcadas como Alpha, Canary o Dev están prohibidas en producción.
- **Nunca adoptar Beta.** Versiones Beta o Preview solo se permiten en ramas experimentales sin impacto en producción.
- **Nunca adoptar RC.** Release Candidates se evalúan caso por caso; requieren aprobación explícita del Arquitecto Principal.
- **Nunca SNAPSHOT.** Dependencias dinámicas o SNAPSHOT están prohibidas en cualquier ambiente.
- **Validar compatibilidad antes de actualizar.** Cada actualización debe pasar por: compilación, tests unitarios, tests instrumentados y smoke test en dispositivo.
- **Registro de cambios.** Toda actualización debe documentarse en el CHANGELOG del proyecto con versión anterior y nueva.

---

## 5. Política de Gradle

### 5.1 Version Catalog obligatorio
- Toda dependencia y plugin debe declararse en `gradle/libs.versions.toml`.
- **Prohibido** hardcodear versiones en módulos individuales.
- El catálogo es la **única fuente de verdad** para versiones.

### 5.2 Plugins mediante alias
- Los plugins se referencian exclusivamente por alias desde el catálogo.
- Ejemplo: `alias(libs.plugins.android.application)`.
- **Prohibido** usar `id("com.android.application") version "..."` en módulos.

### 5.3 Repositorios centralizados
- `settings.gradle.kts` define `pluginManagement` y `dependencyResolutionManagement`.
- **FAIL_ON_PROJECT_REROS** activo para evitar fugas de repositorios en módulos.
- Repositorios permitidos: `google()`, `mavenCentral()`.
- Cualquier repositorio adicional requiere aprobación del Arquitecto Principal.

### 5.4 Wrapper oficial
- El wrapper de Gradle está versionado en `gradle/wrapper/gradle-wrapper.properties`.
- Todos los desarrolladores y CI deben usar el wrapper.
- **Prohibido** invocar Gradle directamente desde una instalación local.

### 5.5 Configuration Cache
- `org.gradle.configuration-cache=true` en `gradle.properties`.
- Acelera builds reutilizando la fase de configuración.
- Requiere que los scripts sean deterministas.

### 5.6 Gradle Cache
- `org.gradle.caching=true` en `gradle.properties`.
- Reutiliza resultados de tareas entre ejecuciones.
- Requiere configuración cacheable.

### 5.7 Parallel Build
- `org.gradle.parallel=true` en `gradle.properties`.
- Ejecuta tareas de módulos en paralelo.
- Beneficioso en proyectos multi-módulo.

### 5.8 KSP Configuration
- `ksp.useKSP2=false` en `gradle.properties`.
- **Obligatorio** para mantener compatibilidad con Kotlin 1.9.22 y KSP 1.9.22-1.0.18.

---

## 6. Política de arquitectura

El proyecto adopta las siguientes arquitecturas y patrones como base inalterable:

- **Clean Architecture.** Separación estricta en capas: dominio, aplicación, infraestructura, presentación. El dominio no depende de ningún framework.
- **Domain-Driven Design (DDD).** El dominio se modela como agregados, entidades, value objects y servicios de dominio.
- **Event-Driven Architecture.** Los eventos de dominio fluyen a través de contratos explícitos, sin acoplamiento directo entre productores y consumidores.
- **MVVM.** La capa de presentación expone `StateFlow`/`ViewModel` como contrato hacia la UI.
- **Repository Pattern.** El acceso a datos se abstrae mediante interfaces en el dominio; las implementaciones residen en infraestructura.
- **Dependency Injection.** Hilt se utiliza como framework de DI en capas externas; el dominio recibe dependencias por constructor.

**Regla de oro:** Ninguna librería de infraestructura podrá filtrarse al dominio. Las dependencias se inyectan desde capas superiores mediante interfaces.

---

## 7. Política de dependencias

Toda dependencia nueva debe cumplir obligatoriamente:

- **Compatibilidad.** Versión compatible con AGP, Kotlin y Gradle declarados en este documento.
- **Mantenimiento activo.** Librería con releases recientes (menos de 12 meses sin actualización).
- **Licencia adecuada.** Licencia permisiva (Apache 2.0, MIT, BSD) o aprobada por legal.
- **Uso real.** Justificación de caso de uso concreto en el proyecto.
- **Justificación técnica.** Explicación de por qué no existe alternativa nativa o en el stack actual.
- **Sin dependencias circulares.** El grafo de dependencias debe permanecer acíclico.
- **Impacto en tamaño.** Evaluación de impacto en APK/AAB para librerías de presentación.

**Proceso de aprobación:**
1. Propuesta técnica firmada por el desarrollador.
2. Revisión del Arquitecto Principal.
3. Actualización de `TECHNOLOGY_BASELINE.md`.
4. Actualización de `gradle/libs.versions.toml`.
5. Merge a `main` solo después de los pasos anteriores.

---

## 8. Política de calidad

- **Compilación.** Todo cambio debe compilar sin errores ni warnings de Room, Hilt, KSP o Compose.
- **Tests.** Las nuevas funcionalidades deben incluir pruebas unitarias y, cuando aplique, pruebas instrumentadas.
- **Documentación.** Todo módulo nuevo debe incluir KDoc en clases públicas y un README de arquitectura local.
- **Compatibilidad.** Los cambios deben validarse en al menos un dispositivo físico y un emulador con API mínima (`minSdk = 24`).
- **Performance.** Las pantallas Compose deben superar 60 FPS en dispositivos de gama media.

---

## 9. Política de cambios

- Ninguna decisión tecnológica podrá modificarse sin actualizar este documento.
- Los cambios a este documento requieren:
  - Propuesta técnica con justificación.
  - Aprobación del Arquitecto Principal.
  - Actualización de versión del documento.
  - Comunicación al equipo en canal oficial.
- Este documento se revisa trimestralmente o ante cambios mayores en el ecosistema Android.
- Las versiones históricas se preservan en control de versiones para trazabilidad.

---

## 10. Checklist de revisión

Antes de proponer cualquier actualización tecnológica, verificar:

- [ ] La versión propuesta es estable (no Alpha, Beta, RC ni SNAPSHOT).
- [ ] La versión es compatible con AGP, Kotlin, Gradle y KSP declarados en este documento.
- [ ] La librería tiene mantenimiento activo y licencia adecuada.
- [ ] Existe un caso de uso concreto y justificación técnica.
- [ ] No introduce dependencias circulares.
- [ ] No rompe la independencia del dominio.
- [ ] Compila sin errores ni warnings de procesadores de anotaciones.
- [ ] Tests unitarios existentes pasan sin regresiones.
- [ ] Se actualizó `gradle/libs.versions.toml` exclusivamente.
- [ ] Se actualizó este documento antes de cualquier modificación.
- [ ] El cambio fue aprobado por el Arquitecto Principal.

---

## 11. Decisiones de persistencia (Room)

Las siguientes decisiones se tomaron durante la activación de Room sobre la capa Data:

- **Foreign Keys:** Las relaciones de catálogo (`ProductEntity -> CategoryEntity`, `ModifierGroupEntity -> ProductEntity`, `ModifierOptionEntity -> ModifierGroupEntity`) utilizan `RESTRICT` en lugar de `CASCADE` para forzar revisión/confirmación humana antes de eliminar registros padre, alineándose con los principios de auditoría y supervisión humana del dominio. La relación `DraftOrderItemEntity -> DraftOrderEntity` mantiene `CASCADE` porque el ciclo de vida de los ítems está estrictamente ligado al pedido.
- **Campos monetarios:** Las entidades `ProductEntity.basePrice` y `ModifierOptionEntity.priceAdjustment` usan `Double`. Se recomienda migrar a `Long` (centavos) en una fase posterior para evitar errores de precisión de punto flotante en cálculos financieros. No se migra en esta fase para no introducir cambios disruptivos sin RepositoryImpl/Mappers.
- **Core Library Desugaring:** Se habilitó `coreLibraryDesugaringEnabled = true` y se agregó la dependencia `desugar-jdk` para resolver advertencias de lint por uso de APIs de Java 21 (`Instant.now`) en dominio con `minSdk = 24`.
- **KSP:** Se mantiene `ksp.useKSP2=false` en `gradle.properties` para asegurar compatibilidad con Kotlin 1.9.22 y KSP 1.9.22-1.0.18.

---

**Fin del documento**
