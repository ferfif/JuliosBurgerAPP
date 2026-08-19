package com.juliosburger.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.juliosburger.domain.model.DraftOrderItem
import com.juliosburger.domain.model.DraftOrderStatus
import com.juliosburger.presentation.state.OrderUiState
import com.juliosburger.presentation.viewmodel.OrderViewModel
import com.juliosburger.presentation.viewmodel.ProductSelectionViewModel

@Composable
fun OrderScreen(
    onBack: () -> Unit,
    onAddProduct: () -> Unit,
    selectionViewModel: ProductSelectionViewModel,
    viewModel: OrderViewModel = hiltViewModel(),
    statusFilter: DraftOrderStatus = DraftOrderStatus.DRAFT
) {
    val orderState by viewModel.state.collectAsStateWithLifecycle()
    val selectionState by selectionViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(statusFilter) {
        viewModel.loadOrdersByStatus(statusFilter)
    }

    val isOrderCreationMode = statusFilter == DraftOrderStatus.DRAFT

    var draftOrderItem by remember { mutableStateOf<DraftOrderItem?>(null) }
    if (isOrderCreationMode) {
        LaunchedEffect(selectionState.product, selectionState.selectedModifiers) {
            draftOrderItem = if (selectionState.product != null) {
                selectionViewModel.buildDraftOrderItem()
            } else null
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when (orderState) {
            is OrderUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is OrderUiState.Success -> {
                val orders = (orderState as OrderUiState.Success).orders
                if (orders.isEmpty() && draftOrderItem == null) {
                val emptyMessage = when (statusFilter) {
                    DraftOrderStatus.DRAFT -> "No hay pedidos en borrador"
                    DraftOrderStatus.PENDING_CASHIER_REVIEW -> "No hay pedidos pendientes de revisión"
                    DraftOrderStatus.CONFIRMED -> "No hay pedidos pendientes de cocina"
                    DraftOrderStatus.COOKING -> "No hay pedidos en cocina"
                    DraftOrderStatus.READY -> "No hay pedidos listos para entregar"
                    DraftOrderStatus.DELIVERED -> "No hay pedidos entregados"
                    DraftOrderStatus.CANCELLED -> "No hay pedidos cancelados"
                }
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(emptyMessage)
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        if (isOrderCreationMode && draftOrderItem != null) {
                            item {
                                val currentItem = draftOrderItem
                                if (currentItem != null) {
                                    DraftOrderItemCard(currentItem)
                                    Spacer(modifier = Modifier.padding(vertical = 4.dp))
                                    Button(
                                        onClick = {
                                            selectionViewModel.createOrderFromSelection(
                                                customerPhone = "+521234567890"
                                            ) { success, error ->
                                                if (success) {
                                                    viewModel.loadOrders()
                                                    selectionViewModel.reset()
                                                }
                                            }
                                        },
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text("Crear pedido")
                                    }
                                }
                            }
                        }
                        items(orders) { order ->
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            ) {
                                Text(text = order.customerPhone)
                                Text(text = order.status.name)
                                Text(text = "${order.items.size} ítem(s)")
                                if (order.status == DraftOrderStatus.DRAFT) {
                                    Spacer(modifier = Modifier.padding(vertical = 4.dp))
                                    Button(
                                        onClick = { viewModel.confirmOrder(order.id.toString()) },
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text("Confirmar pedido")
                                    }
                                }
                                if (order.status == DraftOrderStatus.PENDING_CASHIER_REVIEW) {
                                    Spacer(modifier = Modifier.padding(vertical = 4.dp))
                                    Button(
                                        onClick = { viewModel.acceptOrder(order.id.toString()) },
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text("Aceptar pedido")
                                    }
                                }
                                 if (order.status == DraftOrderStatus.CONFIRMED) {
                                    Spacer(modifier = Modifier.padding(vertical = 4.dp))
                                    Button(
                                        onClick = { viewModel.startCooking(order.id.toString()) },
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text("Iniciar cocina")
                                    }
                                }
                                if (order.status == DraftOrderStatus.COOKING) {
                                    Spacer(modifier = Modifier.padding(vertical = 4.dp))
                                    Button(
                                        onClick = { viewModel.markAsReady(order.id.toString()) },
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text("Marcar como listo")
                                    }
                                }
                                if (order.status == DraftOrderStatus.READY) {
                                    Spacer(modifier = Modifier.padding(vertical = 4.dp))
                                    Button(
                                        onClick = { viewModel.deliverOrder(order.id.toString()) },
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text("Marcar como entregado")
                                    }
                                }
                                if (order.status == DraftOrderStatus.DRAFT ||
                                    order.status == DraftOrderStatus.PENDING_CASHIER_REVIEW ||
                                    order.status == DraftOrderStatus.CONFIRMED ||
                                    order.status == DraftOrderStatus.COOKING ||
                                    order.status == DraftOrderStatus.READY) {
                                    Spacer(modifier = Modifier.padding(vertical = 4.dp))
                                    Button(
                                        onClick = { viewModel.cancelOrder(order.id.toString(), order.status) },
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text("Cancelar pedido")
                                    }
                                }
                            }
                        }
                    }
                }
            }

            is OrderUiState.Error -> {
                val message = (orderState as OrderUiState.Error).message
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = message)
                }
            }
        }

        IconButton(
            onClick = onBack,
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back"
            )
        }

        if (isOrderCreationMode && selectionState.product == null) {
            FloatingActionButton(
                onClick = onAddProduct,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar producto"
                )
            }
        }
    }
}

@Composable
private fun DraftOrderItemCard(item: DraftOrderItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(text = item.productSnapshot.name)
        Text(text = "$ $${item.productSnapshot.basePrice}")
        item.modifierSnapshot.forEach { modifier ->
            Text(text = "+ $${modifier.priceAdjustment} ${modifier.name}")
        }
    }
}
