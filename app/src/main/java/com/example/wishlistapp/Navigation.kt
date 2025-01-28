package com.example.wishlistapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun WishlistApp() {
    val navController: NavHostController = rememberNavController()
    val viewModel: WishViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(Screen.HomeScreen.route) {
            HomeView(modifier = Modifier, viewModel = viewModel, navController = navController)
        }

        composable(Screen.AddWishScreen.route) {
            AddEditDetailView(
                id = 0L,
                viewModel = viewModel,
                navController = navController
            )
        }
    }
}