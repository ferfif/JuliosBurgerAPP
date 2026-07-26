package com.juliosburger.domain.complaints

import com.juliosburger.domain.core.Identifier

data class ComplaintFilter(
    val customerId: Identifier? = null,
    val orderId: Identifier? = null,
    val category: ComplaintCategory? = null,
    val status: ComplaintStatus? = null,
    val priority: ComplaintPriority? = null
)
