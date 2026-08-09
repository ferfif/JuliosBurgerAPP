package com.juliosburger.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.juliosburger.presentation.screen.CategoriesScreen
import com.juliosburger.presentation.screen.ModifierGroupsScreen
import com.juliosburger.presentation.screen.ModifierOptionsScreen
import com.juliosburger.presentation.screen.ProductsScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "categories"
    ) {
        composable(route = "categories") {
            CategoriesScreen(
                onCategoryClick = { category ->
                    navController.navigate("products/${category.id}")
                }
            )
        }

        composable(
            route = "products/{categoryId}"
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId")
            if (categoryId.isNullOrEmpty()) {
                navController.popBackStack()
                return@composable
            }
            ProductsScreen(
                categoryId = categoryId,
                onProductClick = { product ->
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
            val productId = backStackEntry.arguments?.getString("productId")
            if (productId.isNullOrEmpty()) {
                navController.popBackStack()
                return@composable
            }
            ModifierGroupsScreen(
                productId = productId,
                onModifierGroupClick = { group ->
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
            val groupId = backStackEntry.arguments?.getString("groupId")
            if (groupId.isNullOrEmpty()) {
                navController.popBackStack()
                return@composable
            }
            ModifierOptionsScreen(
                groupId = groupId,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
