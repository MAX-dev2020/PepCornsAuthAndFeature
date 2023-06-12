package com.example.pepcorns.authentication

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavController
import com.example.pepcorns.components.Destination
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class EmailPasswordActivity : Activity() {

    // [START declare_auth]
    private var auth: FirebaseAuth = Firebase.auth

    // [END declare_auth]

    internal fun createAccount(email: String, password: String, context: Context, navController: NavController) {
        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user, navController)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        context
                        ,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }

    internal fun signIn(email: String, password: String, navController: NavController, context:Context) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user, navController)
                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        context,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    updateUI(null, navController)
                }
            }
    }

    private fun sendEmailVerification() {
        // [START send_email_verification]
        val user = auth.currentUser!!
        user.sendEmailVerification()
            .addOnCompleteListener(this) { task ->
                // Email Verification sent
            }
        // [END send_email_verification]
    }

    private fun updateUI(user: FirebaseUser?, navController: NavController) {
        navController.navigate(
            Destination.Home.route
        ){
            popUpTo(Destination.Welcome.route){
                inclusive = true
            }
        }
    }

    private fun reload(navController: NavController) {
        navController.navigate(Destination.Home.route) {
            popUpTo(Destination.Welcome.route) {
                inclusive = true
            }
        }
    }

    companion object {
        private const val TAG = "EmailPassword"
    }
}