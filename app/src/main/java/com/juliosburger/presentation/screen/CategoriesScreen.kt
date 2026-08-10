package com.juliosburger.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.juliosburger.domain.model.Category
import com.juliosburger.presentation.state.CategoriesUiState
import com.juliosburger.presentation.viewmodel.CategoriesViewModel

@Composable
fun CategoriesScreen(
    onCategoryClick: (Category) -> Unit,
    onOrdersClick: () -> Unit,
    onCashierQueueClick: () -> Unit = {},
    viewModel: CategoriesViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {
        when (state) {
        is CategoriesUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is CategoriesUiState.Success -> {
            val categories = (state as CategoriesUiState.Success).categories
            if (categories.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No hay categorías disponibles")
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp)
                ) {
                    items(categories) { category ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onCategoryClick(category) }
                                .padding(8.dp)
                        ) {
                            Text(text = category.name)
                            category.description?.let { description ->
                                Text(text = description)
                            }
                        }
                    }
                }
            }
        }

        is CategoriesUiState.Error -> {
            val message = (state as CategoriesUiState.Error).message
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = message)
            }
        }
    }

        FloatingActionButton(
            onClick = onOrdersClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Ver pedidos"
            )
        }

        TextButton(
            onClick = onCashierQueueClick,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Text("Cola de Revisión")
        }
    }
}
