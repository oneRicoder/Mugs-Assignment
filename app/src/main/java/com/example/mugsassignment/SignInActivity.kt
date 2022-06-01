package com.example.mugsassignment

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.mugsassignment.databinding.ActivitySignInBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {
    var binding: ActivitySignInBinding? = null
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var mProgressDialog: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        FirebaseApp.initializeApp(this)
        firebaseAuth = FirebaseAuth.getInstance()
        binding?.tvNotRegisteredYet?.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        binding?.btnSignIn?.setOnClickListener {
            signInRegisteredUser()
        }
    }

    private fun signInRegisteredUser(){
        val email = binding?.etEmail?.text.toString().trim { it <= ' ' }
        val password = binding?.etPassword?.text.toString().trim { it <= ' ' }
        if (validateForm(email, password)){
            showProgressDialog()
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                hideProgressDialog()
                if (it.isSuccessful){
                    startActivity(Intent(this, MainActivity::class.java))
                }else{
                    Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun validateForm(email: String, password: String): Boolean{
        return when{
            TextUtils.isEmpty(email) -> {
                Toast.makeText(this, "Please Enter Your Email Address", Toast.LENGTH_SHORT).show()
                false
            }
            TextUtils.isEmpty(password) -> {
                Toast.makeText(this, "Please Enter Your Password", Toast.LENGTH_SHORT).show()
                false
            }else -> {
                true
            }
        }
    }
    fun showProgressDialog(){
        mProgressDialog = Dialog(this)
        mProgressDialog.setContentView(R.layout.dialog_progress)
        mProgressDialog.show()
    }
    fun hideProgressDialog(){
        mProgressDialog.dismiss()
    }
}