package com.example.wishlistapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun WishlistApp() {
    val navController: NavHostController = rememberNavController()
    val viewModel: WishViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(route = Screen.HomeScreen.route) {
            HomeView(modifier = Modifier, viewModel = viewModel, navController = navController)
        }

        composable(
            route = Screen.AddWishScreen.route + "/{id}",
            arguments = listOf(navArgument(name = "id") {
                type = NavType.LongType
                defaultValue = 0L
                nullable = false
            })
        ) {
            val arg: Long = if (it.arguments != null) it.arguments!!.getLong("id") else 0L
            AddEditDetailView(
                id = arg,
                viewModel = viewModel,
                navController = navController
            )
        }
    }
}