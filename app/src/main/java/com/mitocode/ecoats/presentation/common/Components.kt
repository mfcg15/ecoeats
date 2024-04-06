package com.mitocode.ecoats.presentation.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextFieldComponent(
    modifier: Modifier = Modifier,
    text:String,
    cornerShapeDp: Dp,
    textLabel:String,
    colors: TextFieldColors,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
    onValueChange : (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier,
        value = text,
        onValueChange = {
            onValueChange(it)
        },
        shape = RoundedCornerShape(cornerShapeDp),
        label = {
            Text(
                text = textLabel
            )
        },
        colors = colors,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation
    )
}

@Composable
fun TextAnnotationStringComponent(
    modifier: Modifier = Modifier,
    text: AnnotatedString,
    style: TextStyle
) {
    Text(
        text = text,
        modifier = modifier,
        style = style
    )
}


@Composable
fun OutlinedButtonComponent(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle,
    containerColor: Color,
    contentColor: Color,
    border: BorderStroke,
    onClickButton:()->Unit
) {
    OutlinedButton(
        modifier = modifier,
        onClick = { onClickButton() },
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        border = border
    ) {
        Text(
            text = text,
            style = style
        )
    }
}