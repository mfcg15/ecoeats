package com.mitocode.ecoats.presentation.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import com.mitocode.ecoats.domain.model.Dish
import com.mitocode.ecoats.presentation.common.OutlinedButtonComponent
import com.mitocode.ecoats.presentation.common.TextComponent
import com.mitocode.ecoats.ui.theme.PrimaryButton

private val topAppBarStates = listOf(true,false)
@Composable
fun DishDetailScreen(
    viewmodel: DishDetailViewModel = hiltViewModel(),
    paddingValues : PaddingValues,
    idUser: Int,
    dish: Dish,
    updateCart: (Boolean) -> Unit,
    estado : Boolean,
    onDish: () -> Unit,
)
{
    val state = viewmodel.state
    
    var cantidad by remember {
        mutableStateOf(1)
    }

    LaunchedEffect(key1 = true) {
        viewmodel.isDishCart(idUser,dish.id)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(0.8f))
        {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(dish.thumbails)
                    .crossfade(1000)
                    .build(),
                contentDescription = dish.name,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            IconButton(onClick = {onDish()}) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "ArrowBack",
                    tint = Color.White
                )
            }
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(2f)
            .padding(14.dp))
        {
            Column(modifier = Modifier
                .fillMaxWidth()) {

                TextComponent(
                    text = dish.name,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 26.sp),
                    modifier = Modifier.fillMaxWidth()
                )

                TextComponent(
                    text = dish.description,
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Justify
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )

                TextComponent(
                    text = "Ingredientes",
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                )

                val ingredients = dish.ingredients

                val ingredient = ingredients.split(" | ")

                ingredient.forEach {

                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp))
                    {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 6.dp),
                            verticalAlignment = Alignment.CenterVertically)
                        {

                            TextComponent(
                                text = "●",
                                style = TextStyle(
                                    color = Color.Cyan
                                )
                            )
                            TextComponent(
                                text = "${it}.",
                                style = TextStyle(
                                    fontWeight = FontWeight.Light,
                                    fontSize = 12.sp
                                ),
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }
                    }

                }

                Row (modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                ){
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                    ) {
                        TextComponent(
                            text = "Carbohidratos",
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Row (modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp)
                        ){
                            TextComponent(
                                text = "Número total :",
                                style = TextStyle(
                                    fontWeight = FontWeight.Light,
                                    fontSize = 14.sp
                                ),
                                modifier = Modifier.weight(1f)
                            )
                            TextComponent(
                                text = "${dish.carbohydrates}g",
                                style = TextStyle(
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryButton
                                ),
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                    ) {
                        TextComponent(
                            text = "Precio",
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Row (modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp)
                       ){
                            TextComponent(
                                text = "Total :",
                                style = TextStyle(
                                    fontWeight = FontWeight.Light,
                                    fontSize = 14.sp
                                ),
                                modifier = Modifier.weight(1f)
                            )
                            TextComponent(
                                text = "$${dish.price.toInt()}",
                                style = TextStyle(
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryButton
                                ),
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }

                if(state.isBuy == false)
                {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp)
                    ) {
                        TextComponent(
                            text = "Cantidad",
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp)
                        ) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                                verticalAlignment = Alignment.CenterVertically
                            )
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
                                        if(cantidad !=1)
                                        {
                                            cantidad--
                                        }
                                    },
                                    border = BorderStroke(width = 1.dp, color = Color.DarkGray),
                                    modifier = Modifier
                                        .height(40.dp)
                                )

                                TextComponent(
                                    text = "${cantidad}",
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Normal,
                                        color = PrimaryButton
                                    ), modifier = Modifier.padding(start = 10.dp,end = 10.dp)
                                )

                                OutlinedButtonComponent(
                                    text = "+",
                                    style = TextStyle(
                                        fontStyle = FontStyle.Normal,
                                        fontWeight = FontWeight.Bold,
                                    ),
                                    containerColor = Color.LightGray,
                                    contentColor = Color.DarkGray,
                                    onClickButton = {
                                        if(cantidad !=9)
                                        {
                                            cantidad++
                                        }
                                    },
                                    border = BorderStroke(width = 1.dp, color = Color.DarkGray),
                                    modifier = Modifier
                                        .height(40.dp)
                                )
                            }
                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f))
                            {
                                OutlinedButtonComponent(
                                    text = "Agregar",
                                    style = TextStyle(
                                        fontStyle = FontStyle.Normal,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    containerColor = Color.White,
                                    contentColor = PrimaryButton,
                                    onClickButton = {
                                        viewmodel.saveDishCart(idUser,dish.id,dish.name,dish.image,cantidad,dish.price.toInt())
                                        var auxEstado = if(estado) 1 else 0
                                        updateCart(topAppBarStates[auxEstado])
                                        onDish()
                                    },
                                    border = BorderStroke(width = 1.dp, color = PrimaryButton),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(40.dp)
                                )

                            }
                        }
                    }
                }
            }
        }
    }
}



@Preview (name = "DishDetailScreen", showSystemUi = true)
@Composable
fun DishDetailScreenPreview() {
    DishDetailScreen(paddingValues = PaddingValues(), idUser = 0, dish = Dish(0,"","","http://h3llo.io/eco_eats/eco_eats_arroz_chaufa.jpeg","",0.0,0.0,0.0,0.0,"",false,false), updateCart = {}, estado = false, onDish = {})
}