package com.example.pepcorns.components

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompanyMenu(
    onCompanySelected: (String) -> Unit,
) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    val companies = arrayOf("Apple", "Microsoft", "Tesla")
    var selectedText by remember { mutableStateOf(CompanyTicker.Apple.companyName)}
    val selectedCompany  = companies.map {
        when(it){
             "Apple"-> CompanyTicker.Apple.companyName
            "Microsoft" -> CompanyTicker.Microsoft.companyName
            "Tesla" -> CompanyTicker.Tesla.companyName
            else -> "Apple"
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                value = selectedText,
                onValueChange = { onCompanySelected(it)},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                companies.forEach{ item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedText = selectedCompany[companies.indexOf(item)]
                            expanded = false
                            onCompanySelected(selectedText)
                            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                        }
                    )
                }

            }
        }
    }
}

