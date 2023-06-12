package com.example.pepcorns.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.pepcorns.components.CompanyMenu
import com.example.pepcorns.components.CompanyTicker
import com.example.pepcorns.components.CompanyTickerIndex
import com.example.pepcorns.viewModel.ApiViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onSignOutClicked: () -> Unit,
    viewModel: ApiViewModel
) {
    val isDropdownMenuExpanded = remember { mutableStateOf(false) }
    val  selectedCompany = remember { mutableStateOf(CompanyTicker.Apple.companyName) }

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
                        onDismissRequest = { isDropdownMenuExpanded.value = false },
                        modifier = Modifier.border(1.dp, Color.White)
                    ) {
                        DropdownMenuItem( text = { Text(text = "Sign out")}, onClick = {
                            onSignOutClicked()
                            isDropdownMenuExpanded.value = false
                        },
                            contentPadding = PaddingValues(start = 25.dp)
                        )
                    }
                }
            )
        }
    ) { it ->
        // Content of the home screen
        // You can add your content here
        print(it)
        Spacer(modifier = Modifier.padding(16.dp))
        LaunchedEffect(Unit){
            viewModel.makeApiRequest("/v1/data/quote?symbols=AAPL%2CTSLA%2CMSFT")
        }
        val stockData = viewModel.dashboardData.collectAsState().value
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Column(Modifier.padding(top = 100.dp, start =  20.dp, end = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Real-Time Stock Quotes",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
                Spacer(modifier = Modifier.padding(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Company: ",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White
                    )
                    CompanyMenu { isSelected ->
                        selectedCompany.value = isSelected
                    }
                }

                Card(
                    elevation = CardDefaults.cardElevation(3.dp),
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp).fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (stockData == null) {
                            CircularProgressIndicator(color = Color.White)
                        } else {
                            val selectedCompanyData =
                                stockData.data.firstOrNull { it.ticker == selectedCompany.value }
                            if (selectedCompanyData != null) {
                                Text(
                                    text = selectedCompanyData.name,
                                    style = MaterialTheme.typography.titleLarge,
                                    color = Color.White,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.padding(8.dp))
                                Text(
                                    text = "Ticker: ${selectedCompanyData.ticker}",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = Color.White
                                )
                                Text(
                                    text = "Price: ${selectedCompanyData.price}",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = Color.White
                                )
                                Text(
                                    text = "Day High: ${selectedCompanyData.day_high}",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = Color.White
                                )
                                Text(
                                    text = "Day Low: ${selectedCompanyData.day_low}",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = Color.White
                                )
                                Text(
                                    text = "Day Open: ${selectedCompanyData.day_open}",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = Color.White
                                )
                                Text(
                                    text = "Market Cap: ${selectedCompanyData.market_cap}",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = Color.White
                                )
                                Text(
                                    text = "Previous Close: ${selectedCompanyData.previous_close_price}",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = Color.White
                                )
                                Text(
                                    text = "Volume: ${selectedCompanyData.volume}",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = Color.White
                                )
                                Text(
                                    text = "Last Trade Time: ${selectedCompanyData.last_trade_time}",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
