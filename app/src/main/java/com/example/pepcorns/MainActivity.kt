package com.example.pepcorns

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.pepcorns.components.Destination
import com.example.pepcorns.navigation.NavGraph
import com.example.pepcorns.ui.theme.PepCornsTheme
import com.example.pepcorns.viewModel.ApiViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : ComponentActivity() {
    private lateinit var navController : NavHostController
    private lateinit var auth: FirebaseAuth
    private val dataViewModel by viewModels<ApiViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        setContent {
            PepCornsTheme(dynamicColor = true) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    navController = rememberNavController()
                    NavGraph(
                        navController = navController,
                        viewModel = dataViewModel
                    )
                    if (auth.currentUser != null) {
                        navController.navigate(Destination.Home.route) {
                            popUpTo(Destination.Welcome.route) {
                                inclusive = true
                            }
                        }
                    }
                }
            }
        }
    }
}
