package com.juliosburger.domain.complaints

import com.juliosburger.domain.core.Identifier
import java.time.Instant

data class Complaint(
    val id: Identifier = Identifier.generate(),
    val category: ComplaintCategory,
    val status: ComplaintStatus,
    val priority: ComplaintPriority,
    val customerId: Identifier? = null,
    val orderId: Identifier? = null,
    val description: String,
    val classificationResult: ComplaintClassificationResult? = null,
    val notes: String? = null,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant = Instant.now()
)
