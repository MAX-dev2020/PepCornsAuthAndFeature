package com.example.pepcorns.composables

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.pepcorns.R
import com.example.pepcorns.components.ClickableLoginTextComponent
import com.example.pepcorns.components.SignUpBtn

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    onSignUpClicked: () -> Unit
) {
    var fullNameValue by remember { mutableStateOf("") }
    var emailValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }

    var fullNameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }


    val fullNameInteractionSource = remember { MutableInteractionSource() }
    val emailInteractionSource = remember { MutableInteractionSource() }
    val passwordInteractionSource = remember { MutableInteractionSource() }

    val fullNameIsFocused by fullNameInteractionSource.collectIsFocusedAsState()
    val emailIsFocused by emailInteractionSource.collectIsFocusedAsState()
    val passwordIsFocused by passwordInteractionSource.collectIsFocusedAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(id = R.string.create_account),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = fullNameValue,
            onValueChange = { fullNameValue = it},
            label = {
                Text(
                    text = "Full Name",
                    color = MaterialTheme.colorScheme.secondary
                ) },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colorScheme.secondary,
            ),
            isError = fullNameError,
            trailingIcon = {
                if (fullNameValue.isNotEmpty() && fullNameIsFocused) {
                    IconButton(onClick = { fullNameValue = "" }) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Remove Password",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }
            },
            interactionSource = fullNameInteractionSource
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = emailValue,
            onValueChange = { emailValue = it },
            label = { Text(
                text = "Email",
                color = MaterialTheme.colorScheme.secondary
            ) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colorScheme.secondary,
            ),
            isError = emailError,
            trailingIcon = {
                if (emailValue.isNotEmpty() && emailIsFocused) {
                    IconButton(onClick = { emailValue = "" }) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Remove Password",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }
            },
            interactionSource = emailInteractionSource

        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = passwordValue,
            onValueChange = { passwordValue = it },
            label = { Text(
                text = "Password",
                color = MaterialTheme.colorScheme.secondary
            ) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colorScheme.secondary,
            ),
            isError = passwordError,
            trailingIcon = {
                if (passwordValue.isNotEmpty() && passwordIsFocused) {
                    IconButton(onClick = { passwordValue = "" }) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Remove Password",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }
            },
            interactionSource = passwordInteractionSource
        )

        Spacer(modifier = Modifier.height(20.dp))

        SignUpBtn(
            fullNameValue,
            emailValue,
            passwordValue,
            {
                fullNameError = it
            },
            {
                emailError = it
            },
            {
                passwordError = it
            },
            {onSignUpClicked()}
        )
        Spacer(modifier = Modifier.weight(1f))
        ClickableLoginTextComponent(tryingToLogin = true, onTextSelected = {
            onSignUpClicked()
        })
    }
}
