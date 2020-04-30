package com.example.platform_univ.login.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.platform_univ.login.model.LoginModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

class LoginViewModel : ViewModel() {

    private val model : LoginModel = LoginModel()
    private val TAG = this::class.java.name


    fun logIn(user: String, password: String): LiveData<Task<AuthResult>>? {
        val result: MutableLiveData<Task<AuthResult>>?  = MutableLiveData()

        model.logIn(user, password).addOnCompleteListener {
            Log.e(TAG,"mi result 1 $result")
            result?.value = it
            Log.e(TAG,"mi result 2  ${result?.value}")
        }
        Log.e(TAG,"retorno ${result?.value}")
        return result
    }

    fun onDestroy() {
        model.auth.signOut()
    }

}