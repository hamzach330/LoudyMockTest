package com.hamza.loudly.features.git_repo_listing.presentation.splash


import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hamza.loudly.R
import com.hamza.loudly.navigation.FeatureNavItem
import com.hamza.loudly.ui.theme.AppThemeColor
import com.hamza.loudly.ui.theme.SplashBg
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController) {

    val systemUiController = rememberSystemUiController()
    val systemBarColor = MaterialTheme.colors.primary

    SideEffect {
        systemUiController.setStatusBarColor(
            color = systemBarColor
        )
    }

    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.5f,
            animationSpec = tween(durationMillis = 700,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                })
        )
        delay(2000)
        navController.navigate(FeatureNavItem.GitRepoListScreen.route) {
            popUpTo(0)
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(AppThemeColor)
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.ic_loudly_banner),
                contentDescription = "Logo",
                modifier = Modifier.scale(scale = scale.value)
            )

        }
    }
}