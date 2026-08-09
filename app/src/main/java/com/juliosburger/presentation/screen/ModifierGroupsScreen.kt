package com.juliosburger.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.juliosburger.domain.model.ModifierGroup
import com.juliosburger.presentation.state.ModifierGroupsUiState
import com.juliosburger.presentation.viewmodel.ModifierGroupsViewModel

@Composable
fun ModifierGroupsScreen(
    productId: String,
    onModifierGroupClick: (ModifierGroup) -> Unit,
    onBack: () -> Unit,
    viewModel: ModifierGroupsViewModel = hiltViewModel()
) {
    LaunchedEffect(productId) {
        viewModel.loadModifierGroups(productId)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {
        IconButton(onClick = onBack) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back"
            )
        }

        when (state) {
            is ModifierGroupsUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is ModifierGroupsUiState.Success -> {
                val modifierGroups = (state as ModifierGroupsUiState.Success).modifierGroups
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(modifierGroups) { group ->
                        Text(
                            text = group.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onModifierGroupClick(group) }
                                .padding(8.dp)
                        )
                    }
                }
            }

            is ModifierGroupsUiState.Error -> {
                val message = (state as ModifierGroupsUiState.Error).message
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
