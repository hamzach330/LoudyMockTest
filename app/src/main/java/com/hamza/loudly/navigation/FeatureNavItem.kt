package com.hamza.loudly.navigation

sealed class FeatureNavItem(val route: String) {
    object Splash : FeatureNavItem("splash_screen")
    object GitRepoListScreen : FeatureNavItem("git_repo_list_screen")

}
