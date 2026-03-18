package com.example.firebase.feature_navbar.logic

import AppDestinations
import androidx.navigation.NavHostController

class BottomBarNavigator(
    private val navController: NavHostController
) {
    fun goHome() {
        navController.navigate(AppDestinations.HOME) {
            launchSingleTop = true
        }
    }

    fun goCreate() {
        navController.navigate(AppDestinations.CREATE) {
            launchSingleTop = true
        }
    }

    fun goProfile() {
        navController.navigate(AppDestinations.PROFILE) {
            launchSingleTop = true
        }
    }
}
