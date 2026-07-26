package com.juliosburger.domain.complaints

import com.juliosburger.domain.core.Identifier
import com.juliosburger.domain.core.Result
import kotlinx.coroutines.flow.Flow

interface ComplaintRepository {
    suspend fun save(complaint: Complaint): Result<Complaint>
    suspend fun getById(id: Identifier): Complaint?
    fun filter(filter: ComplaintFilter): Flow<List<Complaint>>
}
