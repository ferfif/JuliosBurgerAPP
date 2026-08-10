package com.juliosburger.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.juliosburger.domain.model.ModifierOption
import com.juliosburger.presentation.state.ModifierOptionsUiState
import com.juliosburger.presentation.viewmodel.ModifierOptionsViewModel
import com.juliosburger.presentation.viewmodel.ProductSelectionViewModel
import java.util.UUID
import kotlinx.coroutines.launch

@Composable
fun ModifierOptionsScreen(
    groupId: String,
    onBack: () -> Unit,
    selectionViewModel: ProductSelectionViewModel,
    onAddItem: () -> Unit,
    viewModel: ModifierOptionsViewModel = hiltViewModel()
) {
    LaunchedEffect(groupId) {
        viewModel.loadModifierOptions(groupId)
    }

    val optionsState by viewModel.state.collectAsStateWithLifecycle()
    val selectionState by selectionViewModel.state.collectAsStateWithLifecycle()

    val groupUuid = UUID.fromString(groupId)
    val currentGroup = selectionState.currentGroup
    val minSelection = currentGroup?.minSelection ?: 0
    val maxSelection = currentGroup?.maxSelection ?: Int.MAX_VALUE
    val selectedModifiersForGroup = selectionState.selectedModifiers.filter {
        it.modifierGroupId == groupUuid
    }

    Column(modifier = Modifier.fillMaxSize()) {
        IconButton(onClick = onBack) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back"
            )
        }

        when (optionsState) {
            is ModifierOptionsUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is ModifierOptionsUiState.Success -> {
                val modifierOptions = (optionsState as ModifierOptionsUiState.Success).modifierOptions
                if (modifierOptions.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No hay opciones disponibles")
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        items(modifierOptions) { option ->
                            val isSelected = selectedModifiersForGroup.any {
                                it.id == option.id
                            }
                            val isChecked = isSelected && option.isActive
                            val isMaxReached = selectedModifiersForGroup.size >= maxSelection && !isSelected
                            val isDisabled = !option.isActive || isMaxReached

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = isChecked,
                                    onCheckedChange = { checked ->
                                        if (isDisabled) return@Checkbox
                                        val newSelected = if (checked) {
                                            selectedModifiersForGroup + option
                                        } else {
                                            selectedModifiersForGroup - option
                                        }
                                        selectionViewModel.updateSelectedModifiersForGroup(
                                            groupId = groupUuid,
                                            modifiers = newSelected
                                        )
                                    },
                                    enabled = option.isActive
                                )
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 8.dp)
                                ) {
                                    Text(text = option.name)
                                    val adjustmentText = if (option.priceAdjustment > 0) {
                                        "+ $${option.priceAdjustment}"
                                    } else if (option.priceAdjustment < 0) {
                                        "- $${kotlin.math.abs(option.priceAdjustment)}"
                                    } else {
                                        "Sin cargo"
                                    }
                                    Text(text = adjustmentText)
                                    if (option.isDefault) {
                                        Text(text = "Seleccionado por defecto")
                                    }
                                    if (!option.isActive) {
                                        Text(text = "No disponible", color = Color.Red)
                                    }
                                }
                            }
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = "Seleccionados: ${selectedModifiersForGroup.size} / $maxSelection",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        if (selectedGroupHasError(minSelection, maxSelection, selectedModifiersForGroup.size)) {
                            Text(
                                text = buildSelectionError(minSelection, maxSelection, selectedModifiersForGroup.size),
                                color = Color.Red,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                        selectionState.validationError?.let { error ->
                            Text(
                                text = error,
                                color = Color.Red,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }

                        val canAdd = !selectedGroupHasError(minSelection, maxSelection, selectedModifiersForGroup.size)
                        val coroutineScope = rememberCoroutineScope()
                        Button(
                            onClick = {
                                coroutineScope.launch {
                                    val item = selectionViewModel.buildDraftOrderItem()
                                    if (item != null) {
                                        onAddItem()
                                    }
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            enabled = canAdd
                        ) {
                            Text("Agregar al pedido")
                        }
                    }
                }
            }

            is ModifierOptionsUiState.Error -> {
                val message = (optionsState as ModifierOptionsUiState.Error).message
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = message)
                }
            }
        }
    }
}

private fun selectedGroupHasError(
    minSelection: Int,
    maxSelection: Int,
    selectedCount: Int
): Boolean {
    return selectedCount < minSelection || selectedCount > maxSelection
}

private fun buildSelectionError(
    minSelection: Int,
    maxSelection: Int,
    selectedCount: Int
): String {
    if (selectedCount < minSelection) {
        return "Debe seleccionar al menos $minSelection opción(es)"
    }
    if (selectedCount > maxSelection) {
        return "No puede seleccionar más de $maxSelection opción(es)"
    }
    return ""
}
