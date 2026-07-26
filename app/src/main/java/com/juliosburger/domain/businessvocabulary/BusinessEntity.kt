package com.juliosburger.domain.businessvocabulary

/**
 * Entidad de negocio detectada dentro de una frase del cliente.
 *
 * @property name Nombre canónico de la entidad (ej. "cebolla").
 * @property confidence Nivel de confianza de la detección, entre 0.0 y 1.0.
 */
data class BusinessEntity(
    val name: String,
    val confidence: Double? = null
)
