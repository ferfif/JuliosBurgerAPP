package com.juliosburger.data.serialization

import kotlinx.serialization.KSerializer

interface SnapshotSerializer {
    fun <T> serialize(value: T, serializer: KSerializer<T>): String
    fun <T> deserialize(value: String, serializer: KSerializer<T>): T
}
