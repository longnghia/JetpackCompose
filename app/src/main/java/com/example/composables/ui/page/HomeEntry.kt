package com.example.composables.ui.page

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.composables.ui.common.LocalSeedColor
import com.example.composables.ui.common.Route
import com.example.composables.ui.common.animatedComposable
import com.example.composables.ui.common.slideInVerticallyComposable
import com.example.composables.ui.page.api.ApiPage
import com.example.composables.ui.page.home.HomePage
import com.example.composables.ui.page.settings.AboutPage
import com.example.composables.ui.page.settings.DonatePage
import com.example.composables.ui.page.settings.appearance.AppearancePage


@Composable
fun HomeEntry() {
    val navController = rememberNavController()

    val onNavigateBack: () -> Unit = {
        with(navController) {
            if (currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED) {
                popBackStack()
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Log.d("TAG", "HomeEntry.LocalSeedColor: ${LocalSeedColor.current}")
        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navController,
            startDestination = Route.HOME
        ) {
            animatedComposable(Route.HOME) {
                HomePage(
                    navigateToSettings = {
                        navController.navigate(Route.SETTINGS) {
                            launchSingleTop = true
                        }
                    },
                    navigateToApi = {
                        navController.navigate(Route.API) {
                            launchSingleTop = true
                        }
                    }
                )
            }
            animatedComposable(Route.API) {
                ApiPage(
                    onNavigateBack = onNavigateBack
                )
            }
            settingsGraph(onNavigateBack = onNavigateBack, onNavigateTo = { route ->
                navController.navigate(route = route) {
                    launchSingleTop = true
                }
            })
        }
    }
}

fun NavGraphBuilder.settingsGraph(
    onNavigateBack: () -> Unit,
    onNavigateTo: (route: String) -> Unit
) {
    navigation(startDestination = Route.ABOUT, route = Route.SETTINGS) {
        animatedComposable(Route.ABOUT) {
            AboutPage(
                onNavigateBack = onNavigateBack,
                onNavigateToDonatePage = { onNavigateTo(Route.DONATE) },
                onNavigateToAppearancePage = { onNavigateTo(Route.APPEARANCE) })
        }
        animatedComposable(Route.APPEARANCE) {
            AppearancePage(
                onNavigateBack = onNavigateBack
            )
        }
        slideInVerticallyComposable(Route.DONATE) {
            DonatePage(
                onNavigateBack = onNavigateBack
            )
        }
    }
}
