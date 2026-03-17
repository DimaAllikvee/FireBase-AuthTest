package com.example.firebase.feature_auth.data

import com.google.firebase.auth.FirebaseAuth


class AuthRepository(

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
) {

    fun isLoggedIn(): Boolean = auth.currentUser != null
}