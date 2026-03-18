package com.example.firebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.firebase.core.navigation.AppNavGraph
import com.example.firebase.core.settings.ThemePreferences
import com.example.firebase.feature_auth.logic.AuthViewModel
import com.example.firebase.feature_auth.ui.AuthScreen
import com.example.firebase.feature_blog.logic.BlogViewModel
import com.example.firebase.ui.theme.FireBaseTheme
import com.example.firebase.feature_navbar.logic.BottomBarNavigator
import com.example.firebase.feature_navbar.ui.AppBottomBar
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val context = LocalContext.current
            val themePrefs = remember { ThemePreferences(context) }
            val darkTheme by themePrefs.darkModeFlow.collectAsState(initial = false)

            SideEffect {
                WindowCompat.setDecorFitsSystemWindows(window, false)
                val insetsController = WindowInsetsControllerCompat(window, window.decorView)
                insetsController.isAppearanceLightStatusBars = !darkTheme
                insetsController.isAppearanceLightNavigationBars = !darkTheme
            }

            FireBaseTheme(
                darkTheme = darkTheme,
                dynamicColor = false
            ) {
                val authViewModel: AuthViewModel = viewModel()
                val authState by authViewModel.uiState.collectAsState()

                if (authState.isLoggedIn) {
                    AppEntry(
                        darkTheme = darkTheme,
                        onDarkThemeChange = { enabled ->
                            lifecycleScope.launch {
                                themePrefs.setDarkMode(enabled)
                            }
                        },
                        onLogoutClick = {
                            authViewModel.logout()
                        }
                    )
                } else {
                    AuthScreen(authViewModel = authViewModel)
                }
            }
        }
    }
}

@Composable
fun AppEntry(
    darkTheme: Boolean,
    onDarkThemeChange: (Boolean) -> Unit,
    onLogoutClick: () -> Unit
) {
    val navController = rememberNavController()
    val navigator = remember(navController) { BottomBarNavigator(navController) }

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val blogViewModel: BlogViewModel = viewModel()

    Scaffold(
        bottomBar = {
            AppBottomBar(
                currentRoute = currentRoute,
                onHomeClick = { navigator.goHome() },
                onCreateClick = { navigator.goCreate() },
                onProfileClick = { navigator.goProfile() }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            AppNavGraph(
                navController = navController,
                darkTheme = darkTheme,
                onDarkThemeChange = onDarkThemeChange,
                blogViewModel = blogViewModel,
                onLogoutClick = onLogoutClick
            )
        }
    }
}
