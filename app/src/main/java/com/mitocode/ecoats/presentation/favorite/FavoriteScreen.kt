package com.mitocode.ecoats.presentation.favorite

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mitocode.ecoats.domain.model.Dish
import com.mitocode.ecoats.presentation.common.TextComponent
import com.mitocode.ecoats.ui.theme.PrimaryButton

@Composable
fun FavoriteScreen(
    viewmodel: FavoriteViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
    idUser : Int
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
                text = "Favoritos",
                style = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 8.dp, bottom = 16.dp)
            )

            if(state.isEmpty == true)
            {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 20.dp, start = 8.dp, bottom = 4.dp, end = 4.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextComponent(
                        text = "Aun no tiene platos favoritos",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Light
                        )
                    )
                }
            }
            else
            {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {

                    state.successfull?.let { dishes ->
                        items(dishes){dish ->
                            DishItem(
                                dish = dish,
                                context = context
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DishItem(
    modifier: Modifier = Modifier,
    dish: Dish,
    context: Context
) {
    Card(modifier = modifier.fillMaxWidth())
    {
        Row(
            modifier = Modifier.fillMaxWidth().background(Color.Unspecified)
        ) {

            AsyncImage(
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .clip(RoundedCornerShape(16.dp)),
                model = ImageRequest.Builder(context)
                    .data(dish.)
                    .crossfade(2000)
                    .build(),
                contentDescription = "Template Dish",
                contentScale = ContentScale.Crop
            )

            Box(modifier = Modifier.padding(start = 20.dp))
            {
                Column (modifier = Modifier.fillMaxWidth()){
                    TextComponent(
                        text = dish.name,
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        ), modifier = Modifier.padding(top = 6.dp)
                    )
                    Row (modifier = Modifier.fillMaxWidth().padding(top = 10.dp)){
                        TextComponent(
                            text = "Carbohidratos :",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal
                            )
                        )
                        TextComponent(
                            text = "${dish.carbohydrates}g",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryButton
                            ), modifier = Modifier.padding(start = 6.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview (name = "FavoriteScreen", showSystemUi = true)
@Composable
fun FavoriteScreenPreview() {
    FavoriteScreen(paddingValues = PaddingValues(), idUser = 0)
}