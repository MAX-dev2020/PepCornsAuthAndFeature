package com.example.pepcorns.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.pepcorns.R
import com.example.pepcorns.viewModel.ApiViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onSignOutClicked: () -> Unit,
    viewModel: ApiViewModel
) {
    val isDropdownMenuExpanded = remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Home", style = MaterialTheme.typography.titleMedium)  },
                actions = {
                    IconButton(
                        onClick = {
                            isDropdownMenuExpanded.value = true
                        }
                    ) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                    DropdownMenu(
                        expanded = isDropdownMenuExpanded.value,
                        onDismissRequest = { isDropdownMenuExpanded.value = false }
                    ) {
                        DropdownMenuItem( text = { Text(text = "Sign out")}, onClick = {
                            onSignOutClicked()
                            isDropdownMenuExpanded.value = false
                        }
                        )
                    }
                }
            )
        }
    ) {
        // Content of the home screen
        // You can add your content here
        print(it)
        Spacer(modifier = Modifier.padding(16.dp))
        LaunchedEffect(Unit){
            viewModel.makeApiRequest("api/v1/dashboardNew")
        }
    }
}