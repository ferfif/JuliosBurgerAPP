package com.juliosburger.domain.ai

data class LLMRequest(
    val prompt: String,
    val conversationHistory: List<String> = emptyList(),
    val parameters: Map<String, Any> = emptyMap()
)

data class LLMResponse(
    val text: String,
    val rawOutput: Map<String, Any> = emptyMap()
)

data class DetectedIntent(
    val intent: String,
    val confidence: Double
)

data class DetectedEntity(
    val type: String,
    val value: String,
    val confidence: Double
)

interface LLMProvider {
    suspend fun generate(request: LLMRequest): LLMResponse
}

interface ConversationGenerationService {
    suspend fun generateReply(request: LLMRequest): LLMResponse
}

interface IntentDetectionService {
    suspend fun detectIntent(message: String, context: Map<String, Any> = emptyMap()): List<DetectedIntent>
}

interface EntityExtractionService {
    suspend fun extractEntities(message: String, context: Map<String, Any> = emptyMap()): List<DetectedEntity>
}
