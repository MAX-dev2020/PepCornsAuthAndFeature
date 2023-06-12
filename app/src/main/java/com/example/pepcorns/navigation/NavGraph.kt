package com.example.pepcorns.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pepcorns.components.Destination
import com.example.pepcorns.composables.HomeScreen
import com.example.pepcorns.composables.SignInScreen
import com.example.pepcorns.composables.SignUpScreen
import com.example.pepcorns.composables.WelcomeScreen
import com.example.pepcorns.viewModel.ApiViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: ApiViewModel
) {
    NavHost(navController = navController, startDestination = Destination.Welcome.route){

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
                } },
                navController = navController
            )
        }

        composable(Destination.Login.route){
            SignInScreen(
                {
                    navController.navigate(Destination.SignUp.route){
                        popUpTo(Destination.Login.route){
                            inclusive = true
                        }
                    }
                },
                navController = navController)
        }

        composable(Destination.Home.route){
            HomeScreen( { navController.navigate(Destination.Welcome.route) },
                viewModel = viewModel)
        }
    }
}