package com.mitocode.ecoats.presentation.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mitocode.ecoats.R
import com.mitocode.ecoats.presentation.home.BottomNavigationItem
import com.mitocode.ecoats.ui.theme.PrimaryButton

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
    enable : Boolean,
    onClickButton: () -> Unit
) {

    Button(
        onClick = { onClickButton() },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            contentColor = contentColor,
            containerColor = containerColor
        ),
        enabled = enable
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
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
    onValueChange : (String) -> Unit,
    supportingText : @Composable (() -> Unit)? = null,
    isError : Boolean
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
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        supportingText =  supportingText,
        isError = isError
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarComponent(
    modifier: Modifier = Modifier,
    cantidad:Int,
    backgroundColor:Color = Color.White,
    onCartShopping:()->Unit
) {
    TopAppBar(
        modifier = modifier,
        title = {
            ImageComponent(
                image = R.drawable.logo_ecoeats,
                description = "Logo Ecoeats",
                modifier = Modifier
                    .fillMaxWidth()
                    .size(25.dp)
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = backgroundColor
        ),
        actions = {

            IconButton(onClick = {
                onCartShopping()
            }) {
                if(cantidad > 0)
                {
                    Text(text = "${cantidad}")
                    BadgedBox(
                        badge = {
                            Badge(
                                content = {
                                    Text(text = "${cantidad}")
                                },
                                containerColor = Color.Cyan,
                                modifier = Modifier.offset(x = (-3).dp)
                            )
                        }
                    ){
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = "Notifications",
                            tint = Color.Black
                        )
                    }
                }
                else
                {
                    Icon(
                        imageVector = Icons.Filled.ShoppingCart,
                        contentDescription = "Notifications",
                        tint = Color.Black
                    )
                }
            }
        }
    )
}


@Composable
fun NavigationBarComponent(
    modifier : Modifier = Modifier,
    items:List<BottomNavigationItem>,
    onNavigationItem:(BottomNavigationItem)->Unit
) {
    var selectedItemIndex by remember {
        mutableStateOf(0)
    }

    NavigationBar(modifier = modifier) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    onNavigationItem(item)
                },
                icon = {
                    Icon(
                        imageVector = if (index == selectedItemIndex) {
                            item.selectedIcon
                        } else item.unSelectedIcon,
                        contentDescription = item.title,
                        tint = if (index == selectedItemIndex) {
                            PrimaryButton
                        }else{
                            Color.Black
                        }
                    )
                },
                label = {
                    Text(text = item.title)
                }
            )
        }
    }
}