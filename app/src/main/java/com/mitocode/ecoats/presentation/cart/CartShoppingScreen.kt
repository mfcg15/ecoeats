package com.mitocode.ecoats.presentation.cart

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mitocode.ecoats.domain.model.Cart
import com.mitocode.ecoats.presentation.common.ButtonComponent
import com.mitocode.ecoats.presentation.common.OutlinedButtonComponent
import com.mitocode.ecoats.presentation.common.TextComponent
import com.mitocode.ecoats.presentation.payments.PaymentViewModel
import com.mitocode.ecoats.ui.theme.PrimaryButton

private val topAppBarStates = listOf(true,false)
@Composable
fun CartShoppingScreen(
    viewmodel : CartShoppingViewModel = hiltViewModel(),
    viewmodelPayment : PaymentViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
    idUser : Int,
    updateCart: (Boolean) -> Unit,
    estado : Boolean,
    onPayment : () -> Unit,
) {

    val state = viewmodel.state
    val context = LocalContext.current


    LaunchedEffect(key1 = true) {
        viewmodel.fetchData(idUser)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {

        Column {
            TextComponent(
                text = "Carrito",
                style = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 8.dp, bottom = 6.dp)
            )

            if (state.isLoading) {
                Column(modifier = Modifier
                    .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center)
                {
                    CircularProgressIndicator(
                        color = PrimaryButton,
                        strokeWidth = 4.dp
                    )
                }
            }

            if (state.error != null) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 20.dp, start = 8.dp, bottom = 4.dp, end = 4.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextComponent(
                        text = state.error+"",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Light
                        )
                    )
                }
            }
            else
            {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp))
                {

                    Box(modifier = Modifier
                        .weight(3f)
                        .fillMaxWidth()
                    )
                    {
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {

                            state.successfull?.let { cartItems ->
                                items(cartItems){cartItem ->
                                    CartItems(
                                        cartItem = cartItem,
                                        context = context,
                                        idUser = idUser,
                                        updateCart = updateCart,
                                        estado = estado,
                                        viewmodel = viewmodel
                                    )
                                }
                            }
                        }
                    }

                    Column(modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(top = 10.dp))
                    {
                        Card(
                            border = BorderStroke(width = 1.dp, color = PrimaryButton),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row (modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)) {
                                TextComponent(
                                    text = "Total a Pagar :",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    ),
                                    modifier = Modifier.weight(1f)
                                )
                                TextComponent(
                                    text = "$${state.total}",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        textAlign = TextAlign.End
                                    ),
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                        Row (modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                            horizontalArrangement = Arrangement.Center){
                            OutlinedButtonComponent(
                                text = "Pagar",
                                style = TextStyle(
                                    fontStyle = FontStyle.Normal,
                                    fontWeight = FontWeight.Bold
                                ),
                                containerColor = Color.White,
                                contentColor = PrimaryButton,
                                onClickButton = {
                                    viewmodel.updateIsPayment(idUser)
                                    val totalPayment = state.total!!
                                    viewmodelPayment.savePayment(totalPayment,idUser)
                                    var auxEstado = if(estado) 1 else 0
                                    updateCart(topAppBarStates[auxEstado])
                                    Toast.makeText(context, "¡Pago realizado!", Toast.LENGTH_LONG).show()
                                    onPayment()
                                },
                                border = BorderStroke(width = 1.dp, color = PrimaryButton),
                                modifier = Modifier.width(140.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CartItems(
    cartItem: Cart,
    context: Context,
    idUser: Int,
    updateCart: (Boolean) -> Unit,
    estado : Boolean,
    viewmodel : CartShoppingViewModel
)
{

    val openDialog = remember{
        mutableStateOf(false)
    }

    Card (
        border = BorderStroke(width = 1.dp,
            color = PrimaryButton)
    )
    {

        Row (modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(8.dp))
        {

            AsyncImage(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(80.dp)
                    .clip(RoundedCornerShape(16.dp)),
                model = ImageRequest.Builder(context)
                    .data(cartItem.dishImagen)
                    .crossfade(2000)
                    .build(),
                contentDescription = "Template Dish",
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(2.6f)
                    .padding(start = 8.dp)
            ) {

                TextComponent(
                    text = cartItem.dishName,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold
                    ), modifier = Modifier.fillMaxWidth()
                )
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)) {
                    TextComponent(
                        text = "Cantidad",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal
                        ), modifier = Modifier.weight(1.2f)
                    )
                    TextComponent(
                        text = "Precio",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal
                        ), modifier = Modifier.weight(1f)
                    )
                    TextComponent(
                        text = "Total",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal
                        ), modifier = Modifier.weight(1f)
                    )
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp)) {
                    TextComponent(
                        text = "${cartItem.cantidad}",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = PrimaryButton
                        ), modifier = Modifier.weight(1.2f)
                    )
                    TextComponent(
                        text = "$${cartItem.precio}",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = PrimaryButton
                        ), modifier = Modifier.weight(1f)
                    )
                    TextComponent(
                        text = "$${cartItem.total}",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = PrimaryButton
                        ), modifier = Modifier.weight(1f)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = {openDialog.value = true}) {
                    Icon(
                        Icons.Filled.Create,
                        contentDescription = "Editar",
                        tint = Color.Black
                    )
                }
                IconButton(onClick = {
                    var auxEstado = if(estado) 1 else 0
                    updateCart(topAppBarStates[auxEstado])
                    viewmodel.DeleteDishCart(cartItem.id,idUser)
                })
                {
                    Icon(
                        Icons.Filled.Delete,
                        contentDescription = "Eliminar",
                        tint = Color.Black
                    )
                }
            }
        }
    }

    var cantidadDish by remember {
        mutableStateOf(cartItem.cantidad)
    }

    if(openDialog.value)
    {
        AlertDialog(
            onDismissRequest = {openDialog.value = false},

            title = {
                TextComponent(
                    text = "EDITAR LA CANTIDAD DE PLATO",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )) },

            text = {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center)
                {
                    Row(verticalAlignment = Alignment.CenterVertically)
                    {
                        OutlinedButtonComponent(
                            text = "-",
                            style = TextStyle(
                                fontStyle = FontStyle.Normal,
                                fontWeight = FontWeight.Bold
                            ),
                            containerColor = Color.LightGray,
                            contentColor = Color.DarkGray,
                            onClickButton = {
                                if(cantidadDish !=1)
                                {
                                    cantidadDish--
                                }
                            },
                            border = BorderStroke(width = 1.dp, color = Color.DarkGray),
                            modifier = Modifier.height(40.dp)
                        )

                        TextComponent(
                            text = "${cantidadDish}",
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            ),modifier = Modifier.padding(start = 10.dp,end = 10.dp)
                        )

                        OutlinedButtonComponent(
                            text = "+",
                            style = TextStyle(
                                fontStyle = FontStyle.Normal,
                                fontWeight = FontWeight.Bold
                            ),
                            containerColor = Color.LightGray,
                            contentColor = Color.DarkGray,
                            onClickButton = {
                                if(cantidadDish !=9)
                                {
                                    cantidadDish++
                                }
                            },
                            border = BorderStroke(width = 1.dp, color = Color.DarkGray),
                            modifier = Modifier.height(40.dp)
                        )
                    }
                } },

            confirmButton = {
                ButtonComponent(
                    text = "ACEPTAR",
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    containerColor = PrimaryButton,
                    contentColor = Color.White,
                    onClickButton = {
                        viewmodel.updateCantidadDishCart(cartItem.id,idUser,cantidadDish)
                        openDialog.value = false
                    },
                    enable = true,
                    modifier = Modifier.fillMaxWidth()
                ) },

            dismissButton = {
                OutlinedButtonComponent(
                    text = "CANCELAR",
                    style = TextStyle(
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold
                    ),
                    containerColor = Color.White,
                    contentColor = PrimaryButton,
                    onClickButton = {
                        cantidadDish = cartItem.cantidad
                        openDialog.value = false },
                    border = BorderStroke(width = 1.dp, color = PrimaryButton),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        )
    }
}
@Preview (name = "CartShoppingScreen", showSystemUi = true)
@Composable
fun CartShoppingScreenPreview() {
    CartShoppingScreen(paddingValues = PaddingValues(), idUser = 0, updateCart = {}, estado = false, onPayment = {})
}