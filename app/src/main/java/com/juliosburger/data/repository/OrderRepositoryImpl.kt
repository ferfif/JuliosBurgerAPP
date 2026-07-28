package com.juliosburger.data.repository

import com.juliosburger.data.dao.DraftOrderDao
import com.juliosburger.data.dao.DraftOrderItemDao
import com.juliosburger.data.mapper.DraftOrderItemMapper
import com.juliosburger.data.mapper.DraftOrderMapper
import com.juliosburger.domain.model.DraftOrder
import com.juliosburger.domain.model.DraftOrderStatus
import com.juliosburger.domain.repository.OrderRepository
import java.time.Instant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OrderRepositoryImpl(
    private val draftOrderDao: DraftOrderDao,
    private val draftOrderItemDao: DraftOrderItemDao,
    private val draftOrderItemMapper: DraftOrderItemMapper = DraftOrderItemMapper()
) : OrderRepository {
    override suspend fun saveDraftOrder(order: DraftOrder): Flow<Result<DraftOrder>> {
        return flow {
            val orderEntity = DraftOrderMapper.mapToEntity(order)
            draftOrderDao.insert(orderEntity)

            order.items.forEach { item ->
                draftOrderItemDao.insert(
                    draftOrderItemMapper.mapToEntity(item).copy(draftOrderId = order.id.toString())
                )
            }

            emit(Result.success(order))
        }
    }

    override suspend fun getDraftOrderById(id: String): Flow<DraftOrder?> {
        return flow {
            val orderEntity = draftOrderDao.getById(id)
            if (orderEntity != null) {
                val itemEntities = draftOrderItemDao.getByDraftOrder(id)
                val items = itemEntities.map { draftOrderItemMapper.mapToDomain(it) }
                emit(DraftOrderMapper.mapToDomain(orderEntity).copy(items = items))
            } else {
                emit(null)
            }
        }
    }

    override suspend fun getDraftOrdersByStatus(status: DraftOrderStatus): Flow<List<DraftOrder>> {
        return flow {
            val orders = draftOrderDao.getByStatus(status.name)
            emit(orders.map { entity ->
                val items = draftOrderItemDao.getByDraftOrder(entity.id).map { draftOrderItemMapper.mapToDomain(it) }
                DraftOrderMapper.mapToDomain(entity).copy(items = items)
            })
        }
    }

    override suspend fun updateDraftOrderStatus(id: String, status: DraftOrderStatus): Flow<Result<DraftOrder>> {
        return flow {
            val current = draftOrderDao.getById(id)
            if (current != null) {
                val updated = current.copy(
                    status = status.name,
                    updatedAt = Instant.now().toEpochMilli()
                )
                draftOrderDao.insert(updated)
                val items = draftOrderItemDao.getByDraftOrder(id).map { draftOrderItemMapper.mapToDomain(it) }
                emit(Result.success(DraftOrderMapper.mapToDomain(updated).copy(items = items)))
            } else {
                emit(Result.failure(NoSuchElementException("DraftOrder not found: $id")))
            }
        }
    }
}
