package com.example.pepcorns.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pepcorns.R

@Composable
fun SignUpBtn(
    fullNameValue: String,
    emailValue: String,
    passwordValue: String,
    fullNameError: (Boolean) -> Unit,
    emailError: (Boolean) -> Unit,
    passwordError: (Boolean) -> Unit,
    onRegisterClicked: () -> Unit,
){
    Button(
        onClick = {
            if(fullNameValue.isNotEmpty() && emailValue.isNotEmpty() && passwordValue.isNotEmpty()){
                if(fullNameValue.contains(Regex("[a-zA-Z]"))){
                    println("Please enter a valid name")
                    onRegisterClicked()
                } else {
                    fullNameError(true)
                }
            }
            else {
                if (fullNameValue.isEmpty()) {
                    fullNameError(true)
                } else if (fullNameValue.isNotEmpty()) {
                    fullNameError(false)
                }
                if ( emailValue.isEmpty()) {
                    emailError(true)
                } else if (emailValue.isNotEmpty()) {
                    emailError(false)
                }
                if (passwordValue.isEmpty()) {
                    passwordError(true)
                } else if (passwordValue.isNotEmpty()) {
                    passwordError(false)
                }
            }


        },
        modifier = Modifier
            .size(width = 200.dp, height = 70.dp)
            .padding(top = 20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.primary
        ),
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
    ) {
        Text(text = "Sign Up",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun SignInBtn(
    emailValue: String,
    passwordValue: String,
    emailError: (Boolean) -> Unit,
    passwordError: (Boolean) -> Unit,
    onLogInClicked: () -> Unit,
){
    Button(
        onClick = {
            if(emailValue.isNotEmpty() && passwordValue.isNotEmpty()){
                onLogInClicked()
            }
            else {
                if ( emailValue.isEmpty()) {
                    emailError(true)
                } else if (emailValue.isNotEmpty()) {
                    emailError(false)
                }
                if (passwordValue.isEmpty()) {
                    passwordError(true)
                } else if (passwordValue.isNotEmpty()) {
                    passwordError(false)
                }
            }


        },
        modifier = Modifier
            .size(width = 200.dp, height = 70.dp)
            .padding(top = 20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.primary
        ),
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
    ) {
        Text(text = "Log In",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ClickableLoginTextComponent(tryingToLogin: Boolean = true, onTextSelected: (String) -> Unit) {
    val initialText =
        if (tryingToLogin) "Already have an account? " else "Don’t have an account yet? "
    val loginText = if (tryingToLogin) "Login" else "Sign Up"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(
            color = MaterialTheme.colorScheme.secondary,
            textDecoration = TextDecoration.Underline,
            fontWeight = FontWeight.Bold
        )) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
    }
    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 21.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.secondary,
            fontFamily = FontFamily(Font(R.font.gill_sans))
        ),
        text = annotatedString,
        onClick = { offset ->

            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->
                    Log.d("ClickableTextComponent", "{${span.item}}")

                    if (span.item == loginText) {
                        onTextSelected(span.item)
                    }
                }
        },
    )
}
