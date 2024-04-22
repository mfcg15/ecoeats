package com.mitocode.ecoats.presentation.welcome

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mitocode.ecoats.R
import com.mitocode.ecoats.presentation.common.ImageComponent
import com.mitocode.ecoats.presentation.common.SpacerComponent
import com.mitocode.ecoats.presentation.common.TextComponent
import com.mitocode.ecoats.presentation.common.ButtonComponent
import com.mitocode.ecoats.presentation.on_boarding.SaveOnBoardingScreen
import com.mitocode.ecoats.ui.theme.Primary
import com.mitocode.ecoats.ui.theme.PrimaryButton

@Composable
fun WelcomeScreen(onNavigateOnBoarding: () -> Unit, onNavigateLogin: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            WelcomeHeader()
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.BottomCenter
        ) {
            WelcomeContent(
                onNavigateOnBoarding = { onNavigateOnBoarding()},
                onNavigateLogin = { onNavigateLogin() }
            )
        }

    }

}

@Composable
fun WelcomeHeader() {

    Column {

        ImageComponent(
            image = R.drawable.logo_ecoeats,
            description = "Logo Ecoeats",
            modifier = Modifier
                .fillMaxWidth()
                .size(25.dp)
        )
        SpacerComponent(modifier = Modifier.height(12.dp))
        TextComponent(
            text = "Una vida saludable",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                color = Primary
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun WelcomeContent(onNavigateOnBoarding: () -> Unit, onNavigateLogin: () -> Unit) {

    val context = LocalContext.current

    val saveScreen = SaveOnBoardingScreen(context)

    ImageComponent(
        image = R.drawable.background_fruits,
        description = "Background Fruits",
        modifier = Modifier.fillMaxSize()
    )

    ButtonComponent(
        text = "Empezar",
        style = TextStyle(
            fontWeight = FontWeight.Bold
        ),
        contentColor = Color.White,
        containerColor = PrimaryButton,
        modifier = Modifier.padding(bottom = 50.dp),
        onClickButton = {

            var flag = saveScreen.getOnBoardingScreen()

            if(flag)
            {
                onNavigateLogin()
            }
            else
            {
                onNavigateOnBoarding()
            }
        },
        enable = true
    )
}

@Preview(showSystemUi = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(onNavigateOnBoarding = {}, onNavigateLogin = {})
}