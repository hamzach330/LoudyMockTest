package com.hamza.loudly.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hamza.loudly.features.git_repo_listing.presentation.git_repo_listing.GitRepoListingScreen
import com.hamza.loudly.features.git_repo_listing.presentation.splash.SplashScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = FeatureNavItem.Splash.route //First Screen/Splash
    ) {
        //here we define our navigation routes with their destinations
        composable(route = FeatureNavItem.Splash.route) {
            SplashScreen(navController)
        }

        composable(route = FeatureNavItem.GitRepoListScreen.route) {
            GitRepoListingScreen(navController = navController)
        }
    }
}