package com.mitocode.ecoats.presentation.login

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mitocode.ecoats.R
import com.mitocode.ecoats.presentation.common.ButtonComponent
import com.mitocode.ecoats.presentation.common.ImageComponent
import com.mitocode.ecoats.presentation.common.OutlinedButtonComponent
import com.mitocode.ecoats.presentation.common.TextComponent
import com.mitocode.ecoats.ui.theme.PrimaryButton
import com.mitocode.ecoats.presentation.common.OutlinedTextFieldComponent
import com.mitocode.ecoats.presentation.common.SpacerComponent
import com.mitocode.ecoats.presentation.common.TextAnnotationStringComponent

@Composable
fun LoginScreen(
    viewmodel: LoginViewModel = hiltViewModel(),
    onNavigateHome: () -> Unit,
    onNavigateRegister: () -> Unit
) {

    val state = viewmodel.state
    val context = LocalContext.current

    LaunchedEffect(key1 = state.successfull, key2 = state.error) {
        if (state.successfull != null) {
            Toast.makeText(context, "Bienvenido", Toast.LENGTH_LONG).show()
            onNavigateHome()
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            HeaderLogin()
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(4f)
                .padding(start = 24.dp, end = 24.dp, top = 24.dp)
        ) {
            ContentLogin(viewmodel)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(4f)
                .padding(top = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FooterLogin(onNavigateRegister = { onNavigateRegister() })
        }
    }

}

@Composable
fun HeaderLogin() {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        ImageComponent(
            image = R.drawable.logo_ecoeats,
            description = "Logo Eco eats",
            modifier = Modifier.size(150.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentLogin(viewmodel: LoginViewModel) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var visualTransformation by remember {
        mutableStateOf(false)
    }

    TextComponent(
        text = "Login",
        style = TextStyle(color = PrimaryButton, fontSize = 28.sp, fontWeight = FontWeight.Bold)
    )

    OutlinedTextFieldComponent(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        text = email,
        cornerShapeDp = 24.dp,
        textLabel = "Correo",
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = PrimaryButton,
            unfocusedBorderColor = Color.Black
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        trailingIcon = {

            if (email != "") {
                IconButton(onClick = { email = "" }) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = "Clear"
                    )
                }
            }

        },

        onValueChange = {
            email = it
        },

        supportingText = {},
        isError = false
    )

    OutlinedTextFieldComponent(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        text = password,
        cornerShapeDp = 24.dp,
        textLabel = "Contraseña",
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = PrimaryButton,
            unfocusedBorderColor = Color.Black
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        trailingIcon = {

            if (password != "") {
                IconButton(onClick = { visualTransformation = !visualTransformation }) {
                    Icon(
                        imageVector = if (visualTransformation) {
                            Icons.Filled.Visibility
                        } else {
                            Icons.Filled.VisibilityOff
                        },
                        contentDescription = "Clear"
                    )
                }
            }
        },
        visualTransformation = if (visualTransformation) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },

        onValueChange = {
            password = it
        },

        supportingText = {},
        isError = false
    )

    if (viewmodel.state.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = PrimaryButton,
                strokeWidth = 4.dp
            )
        }
    }

    if (viewmodel.state.error != null) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            TextComponent(
                text = "${viewmodel.state.error}",
                style = TextStyle(color = Color.Red,textAlign = TextAlign.Center)
            )
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {

        ButtonComponent(
            text = "Ingresar",
            style = TextStyle(fontWeight = FontWeight.Bold),
            containerColor = PrimaryButton,
            contentColor = Color.White,
            onClickButton = {
                viewmodel.signIn(email, password)
            },
            enable = if (email == "" || password == "") false else true,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        )
    }

}

@Composable
fun FooterLogin(onNavigateRegister: () -> Unit) {

    TextAnnotationStringComponent(
        text = buildAnnotatedString {
            append("¿Olvidaste tu contraseña?.")
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = PrimaryButton)) {
                append(" Ingrese aquí")
            }
        },
        style = TextStyle(
            fontWeight = FontWeight.Normal
        )
    )

    SpacerComponent(modifier = Modifier.height(64.dp))

    TextComponent(
        text = "¿Usted no tiene una cuenta?",
        style = TextStyle(fontWeight = FontWeight.Normal)
    )

    SpacerComponent(modifier = Modifier.height(16.dp))

    OutlinedButtonComponent(
        text = "Registrate",
        style = TextStyle(
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold
        ),
        containerColor = Color.White,
        contentColor = PrimaryButton,
        onClickButton = {
            onNavigateRegister()
        },
        border = BorderStroke(width = 1.dp, color = PrimaryButton),
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 24.dp)
    )
}

@Preview(name = "LoginScreen", showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(onNavigateHome = {}, onNavigateRegister = {})
}