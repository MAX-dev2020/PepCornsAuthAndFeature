package com.example.pepcorns.components

sealed class Destination(val route: String) {
    object Welcome : Destination("welcomeScreen")
    object Login : Destination("signInScreen")
    object Home : Destination("homeScreen")
    object SignUp : Destination("signUpScreen")
}
