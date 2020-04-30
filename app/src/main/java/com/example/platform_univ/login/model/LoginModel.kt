package com.example.platform_univ.login.model

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginModel {

    val auth= FirebaseAuth.getInstance()

    fun logIn(user: String, password: String) : Task<AuthResult> {
        return auth.signInWithEmailAndPassword(user, password)
    }

}