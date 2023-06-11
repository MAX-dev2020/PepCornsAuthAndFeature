package com.example.pepcorns

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pepcorns.components.Destination
import com.example.pepcorns.composables.HomeScreen
import com.example.pepcorns.composables.SignUpScreen
import com.example.pepcorns.composables.SignInScreen
import com.example.pepcorns.composables.WelcomeScreen
import com.example.pepcorns.ui.theme.PepCornsTheme
import com.google.firebase.auth.ktx.actionCodeSettings


class MainActivity : ComponentActivity() {
    internal lateinit var navController : NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PepCornsTheme(dynamicColor = true) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    navController = rememberNavController()

                    NavHost(navController = navController as NavHostController, startDestination = Destination.Welcome.route){

                        composable(Destination.Welcome.route){
                            WelcomeScreen(
                                onNavigateToSignUp = { navController.navigate(Destination.SignUp.route) }
                            ) { navController.navigate(Destination.Login.route) }
                        }

                        composable(Destination.SignUp.route){
                            SignUpScreen(
                                onSignUpClicked = { navController.navigate(Destination.Login.route){
                                    popUpTo(Destination.SignUp.route){
                                        inclusive = true
                                    }
                                } }
                            )
                        }

                        composable(Destination.Login.route){
                            SignInScreen(
                                onLogInClicked = { navController.navigate(Destination.Home.route){
                                    popUpTo(Destination.Login.route){
                                        inclusive = true
                                    }
                                } },
                            ){
                                  navController.navigate(Destination.SignUp.route){
                                      popUpTo(Destination.Login.route){
                                          inclusive = true
                                      }
                                  }
                            }
                        }

                        composable(Destination.Home.route){
                            HomeScreen()
                        }
                    }
                }
            }
        }
    }
}
