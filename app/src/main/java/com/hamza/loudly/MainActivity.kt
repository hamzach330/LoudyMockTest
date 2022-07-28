package com.hamza.loudly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import com.hamza.loudly.navigation.NavGraph
import com.hamza.loudly.ui.theme.LoudlyTestTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoudlyTestTheme {
                navController = rememberNavController()
                //Nav graph will handle all the navigation in the app.
                //With compose, its different than the normal navigation.
                NavGraph(navController = navController)
            }
        }
    }
}
