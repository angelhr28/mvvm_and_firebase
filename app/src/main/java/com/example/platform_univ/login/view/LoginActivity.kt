package com.example.platform_univ.login.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.platform_univ.R
import com.example.platform_univ.login.ViewModel.LoginViewModel
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.AuthResult
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity(){

    private lateinit var contEdtUser   : TextInputLayout
    private lateinit var contEdtPass   : TextInputLayout
    private lateinit var edtUser       : TextInputEditText
    private lateinit var edtPass       : TextInputEditText
    private lateinit var btnLogin      : Button
    private lateinit var lblCreateUser : TextView
    private lateinit var presenter     : LoginViewModel

    private val viewModel by lazy{ ViewModelProviders.of(this).get(LoginViewModel::class.java)}
    private val TAG = this::class.java.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contEdtUser   = cont_edt_user
        edtUser       = edt_user
        contEdtPass   = cont_edt_pass
        edtPass       = edt_pass
        btnLogin      = btn_login
        lblCreateUser = lbl_create_user

        btnLogin.setOnClickListener {
            val user    = edtUser.text?.trim().toString()
            val password= edtPass.text?.trim().toString()
            if (user.isEmpty()){
                showToask("Ingresa un usuario.")
                return@setOnClickListener
            }

            if (password.isEmpty()){
                showToask("Ingresa una contraseña.")
                return@setOnClickListener
            }

            showProgres()
            viewModel.logIn(user, password)
                ?.observe(this, Observer<Task<AuthResult>> {
                    Log.e(TAG,"result View  ${it.isSuccessful}")
                    if(it.isSuccessful) logInSuccess()
                    else logInError()
                    hideProgres()
                }
                )
        }

        lblCreateUser.setOnClickListener {
            val intent = Intent(this,  PerfilActivity::class.java)
            startActivity(intent)
        }
    }


    fun showProgres() {
//        Log.e(TAG,"cargando")
    }

    fun hideProgres() {
//        Log.e(TAG,"termine")
    }

    fun showToask(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()}

    fun logInSuccess() {
        val intent = Intent(this,  PerfilActivity::class.java)
        startActivity(intent)
    }

    fun logInError() {
        edtUser.setText("")
        edtPass.setText("")
        edtUser.clearFocus()
        edtPass.clearFocus()
        showToask("Usuarion o contraseña incorrecta.")
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

}
