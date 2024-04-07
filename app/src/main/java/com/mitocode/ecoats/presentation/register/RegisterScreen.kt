package com.mitocode.ecoats.presentation.register

import android.util.Log
import android.widget.ImageButton
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mitocode.ecoats.presentation.common.ButtonComponent
import com.mitocode.ecoats.presentation.common.OutlinedTextFieldComponent
import com.mitocode.ecoats.presentation.common.TextAnnotationStringComponent
import com.mitocode.ecoats.presentation.common.TextComponent
import com.mitocode.ecoats.ui.theme.PrimaryButton

@Composable
fun RegisterScreen(
    onNavigateLogin : () -> Unit,
    onNavigateHome: () -> Unit
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(end = 24.dp, top = 24.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp)
                .weight(1f)
        ) {
            HeaderRegister(onNavigateLogin = { onNavigateLogin()})
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp)
                .weight(9f)
        ) {
            ContentRegister(onNavigateHome = { onNavigateHome()})
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(3f)
                .padding(start = 24.dp, top = 70.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FooterRegister(onNavigateLogin = { onNavigateLogin()})
        }
    }

}

@Composable
fun HeaderRegister(onNavigateLogin : () -> Unit) {

    Row (verticalAlignment = Alignment.CenterVertically){

        IconButton(onClick = {onNavigateLogin()}) {
            Icon(

                Icons.Filled.ArrowBack,
                contentDescription = "ArrowBack",
                tint = PrimaryButton
            )
        }

        TextComponent(
            text = "Registro",
            style = TextStyle(color = PrimaryButton, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentRegister(onNavigateHome: () -> Unit) {

    var nombre by remember {
        mutableStateOf("")
    }

    var apellido by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var passwordConfir by remember {
        mutableStateOf("")
    }

    var visualTransformation by remember {
        mutableStateOf(false)
    }

    OutlinedTextFieldComponent(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        text = nombre,
        cornerShapeDp = 24.dp,
        textLabel = "Nombres",
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = PrimaryButton,
            unfocusedBorderColor = Color.Black
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        trailingIcon = {

            if(nombre != "")
            {
                IconButton(onClick = { nombre = "" }) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = "Clear"
                    )
                }
            }

        },

        onValueChange = {
            nombre = it
        }
    )

    OutlinedTextFieldComponent(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        text = apellido,
        cornerShapeDp = 24.dp,
        textLabel = "Apellidos",
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = PrimaryButton,
            unfocusedBorderColor = Color.Black
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        trailingIcon = {

            if(apellido != "")
            {
                IconButton(onClick = { apellido = "" }) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = "Clear"
                    )
                }
            }
        },

        onValueChange = {
            apellido = it
        }
    )

    OutlinedTextFieldComponent(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
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

            if(email != "")
            {
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
        }
    )

    OutlinedTextFieldComponent(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        text = password,
        cornerShapeDp = 24.dp,
        textLabel = "Contraseña",
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = PrimaryButton,
            unfocusedBorderColor = Color.Black
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next
        ),

        trailingIcon = {

            if(password != "")
            {
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
        }
    )

    OutlinedTextFieldComponent(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        text = passwordConfir,
        cornerShapeDp = 24.dp,
        textLabel = "Confirmar contraseña",
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = PrimaryButton,
            unfocusedBorderColor = Color.Black
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),

        trailingIcon = {

            if(passwordConfir != "")
            {
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
            passwordConfir = it
        }
    )

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {

        ButtonComponent(
            text = "Registrarme",
            style = TextStyle(fontWeight = FontWeight.Bold),
            containerColor = PrimaryButton,
            contentColor = Color.White,
            onClickButton = {
                onNavigateHome()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        )
    }
}

@Composable
fun FooterRegister(onNavigateLogin : () -> Unit) {

    val texto = buildAnnotatedString {
        append("¿Ya tienes una cuenta? ")
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = PrimaryButton)) {
            pushStringAnnotation(tag = " Inicia Sesión", annotation = " Inicia Sesión")
            append(" Inicia Sesión")
        }
    }

    ClickableText(style = TextStyle(
        fontWeight = FontWeight.Normal
    ), text = texto, onClick = {
        word -> texto.getStringAnnotations(word,word).firstOrNull()?.also {
            span -> if (span.item == " Inicia Sesión"){
                onNavigateLogin()
            }
    }
    })
}

@Preview (name = "RegisterScreen", showSystemUi = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(onNavigateLogin = {}, onNavigateHome = {})
}