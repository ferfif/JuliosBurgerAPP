package com.juliosburger.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.juliosburger.domain.model.DraftOrderStatus
import com.juliosburger.presentation.screen.CategoriesScreen
import com.juliosburger.presentation.screen.ModifierGroupsScreen
import com.juliosburger.presentation.screen.ModifierOptionsScreen
import com.juliosburger.presentation.screen.OrderScreen
import com.juliosburger.presentation.screen.ProductsScreen
import com.juliosburger.presentation.viewmodel.ProductSelectionViewModel

@Composable
fun NavGraph(navController: NavHostController) {
    val sharedEntry = remember(navController) {
        navController.getBackStackEntry("categories")
    }

    NavHost(
        navController = navController,
        startDestination = "categories"
    ) {
        composable(route = "categories") {
            CategoriesScreen(
                onCategoryClick = { category ->
                    navController.navigate("products/${category.id}")
                },
                onOrdersClick = {
                    navController.navigate("orders")
                },
                onCashierQueueClick = {
                    navController.navigate("cashier_queue")
                },
                onKitchenQueueClick = {
                    navController.navigate("kitchen_queue")
                },
                onCookingQueueClick = {
                    navController.navigate("cooking_queue")
                }
            )
        }

        composable(route = "orders") {
            val selectionViewModel: ProductSelectionViewModel = hiltViewModel(sharedEntry)
            OrderScreen(
                onBack = {
                    navController.popBackStack()
                },
                onAddProduct = {
                    navController.navigate("categories")
                },
                selectionViewModel = selectionViewModel
            )
        }

        composable(
            route = "products/{categoryId}"
        ) { backStackEntry ->
            val selectionViewModel: ProductSelectionViewModel = hiltViewModel(sharedEntry)
            val categoryId = backStackEntry.arguments?.getString("categoryId")
            if (categoryId.isNullOrEmpty()) {
                navController.popBackStack()
                return@composable
            }
            ProductsScreen(
                categoryId = categoryId,
                onProductClick = { product ->
                    selectionViewModel.selectProduct(product)
                    navController.navigate("modifierGroups/${product.id}")
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = "modifierGroups/{productId}"
        ) { backStackEntry ->
            val selectionViewModel: ProductSelectionViewModel = hiltViewModel(sharedEntry)
            val productId = backStackEntry.arguments?.getString("productId")
            if (productId.isNullOrEmpty()) {
                navController.popBackStack()
                return@composable
            }
            ModifierGroupsScreen(
                productId = productId,
                onModifierGroupClick = { group ->
                    selectionViewModel.selectGroup(group)
                    navController.navigate("modifierOptions/${group.id}")
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = "modifierOptions/{groupId}"
        ) { backStackEntry ->
            val selectionViewModel: ProductSelectionViewModel = hiltViewModel(sharedEntry)
            val groupId = backStackEntry.arguments?.getString("groupId")
            if (groupId.isNullOrEmpty()) {
                navController.popBackStack()
                return@composable
            }
            ModifierOptionsScreen(
                groupId = groupId,
                onBack = {
                    navController.popBackStack()
                },
                selectionViewModel = selectionViewModel,
                onAddItem = {
                    navController.popBackStack("orders", inclusive = false)
                }
            )
        }

        composable(route = "cashier_queue") {
            val selectionViewModel: ProductSelectionViewModel = hiltViewModel(sharedEntry)
            OrderScreen(
                onBack = {
                    navController.popBackStack()
                },
                onAddProduct = {
                    navController.navigate("categories")
                },
                selectionViewModel = selectionViewModel,
                statusFilter = DraftOrderStatus.PENDING_CASHIER_REVIEW
            )
        }

        composable(route = "kitchen_queue") {
            val selectionViewModel: ProductSelectionViewModel = hiltViewModel(sharedEntry)
            OrderScreen(
                onBack = {
                    navController.popBackStack()
                },
                onAddProduct = {
                    navController.navigate("categories")
                },
                selectionViewModel = selectionViewModel,
                statusFilter = DraftOrderStatus.CONFIRMED
            )
        }

        composable(route = "cooking_queue") {
            val selectionViewModel: ProductSelectionViewModel = hiltViewModel(sharedEntry)
            OrderScreen(
                onBack = {
                    navController.popBackStack()
                },
                onAddProduct = {
                    navController.navigate("categories")
                },
                selectionViewModel = selectionViewModel,
                statusFilter = DraftOrderStatus.COOKING
            )
        }
    }
}
