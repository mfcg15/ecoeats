package com.mitocode.ecoats.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle

@Composable
fun ImageComponent(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int,
    description: String
) {
    Image(
        painter = painterResource(id = image),
        contentDescription = description,
        modifier = modifier
    )
}

@Composable
fun SpacerComponent(modifier: Modifier = Modifier) {
    Spacer(modifier = modifier)
}

@Composable
fun TextComponent(modifier: Modifier = Modifier, text: String, style: TextStyle) {

    Text(text = text, modifier = modifier, style = style)
}

@Composable
fun ButtonComponent(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle,
    containerColor: Color,
    contentColor: Color,
    onClickButton: () -> Unit
) {

    Button(
        onClick = { onClickButton() },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            contentColor = contentColor,
            containerColor = containerColor
        )
    ) {
        Text(text = text, style = style)
    }
}