package com.juliosburger.data.serialization

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

class KotlinxSnapshotSerializer : SnapshotSerializer {
    override fun <T> serialize(value: T, serializer: KSerializer<T>): String {
        return Json.Default.encodeToString(serializer, value)
    }

    override fun <T> deserialize(value: String, serializer: KSerializer<T>): T {
        return Json.Default.decodeFromString(serializer, value)
    }
}
