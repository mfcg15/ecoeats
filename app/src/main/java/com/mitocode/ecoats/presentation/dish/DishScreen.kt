package com.mitocode.ecoats.presentation.dish

import android.content.Context
import android.widget.RatingBar
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.DensitySmall
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.DensitySmall
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.mitocode.ecoats.domain.model.Dish
import com.mitocode.ecoats.presentation.common.SpacerComponent
import com.mitocode.ecoats.presentation.common.TextComponent
import com.mitocode.ecoats.ui.theme.PrimaryButton


@OptIn(ExperimentalPagerApi::class)
@Composable
fun DishScreen(
    viewmodel: DishViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
) {

    val state = viewmodel.state
    val context = LocalContext.current

    val gridList = remember {
        mutableStateOf(false)
    }

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

    LaunchedEffect(key1 = true) {
        viewmodel.getDishes()
    }

    if (state.error != null) {
        Toast.makeText(context, state.error, Toast.LENGTH_LONG).show()
    }

    val pagerState = rememberPagerState()


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {

        Column {
            TextComponent(
                text = "¿Que hay de nuevo?",
                style = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 8.dp, bottom = 16.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                state.successfull?.let { dishes ->
                    val dishesFlag = dishes.filter {
                        it.flagHeader
                    }
                    HorizontalPager(
                        count = dishesFlag.size,
                        state = pagerState,
                        verticalAlignment = Alignment.Top
                    ) { page ->
                        PagerHeaderHomeComponent(
                            dishesFlag[page],
                            onSelectedItem = {

                            }
                        )
                    }
                    Row(
                        modifier = Modifier.padding(bottom = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        repeat(dishesFlag.size) { iteration ->
                            val color =
                                if (pagerState.currentPage == iteration) PrimaryButton else Color.White
                            Box(
                                modifier = Modifier
                                    .padding(2.dp)
                                    .clip(CircleShape)
                                    .background(color)
                                    .size(12.dp)
                            )
                        }
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 8.dp, bottom = 4.dp, end = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                TextComponent(
                    text = "Carta del día",
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Black
                    )
                )

                if(!gridList.value)
                {
                    IconButton(onClick = {
                        gridList.value = true
                    })
                    {
                        Icon(
                            imageVector = Icons.Filled.List,
                            tint = Color.Black,
                            contentDescription = "GridList",
                        )
                    }
                }
                else
                {
                    IconButton(onClick = {
                        gridList.value = false
                    })
                    {
                        Icon(
                            imageVector = Icons.Filled.GridView,
                            tint = Color.Black,
                            contentDescription = "GridList",
                        )
                    }
                }
            }

            if(gridList.value)
            {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {

                    state.successfull?.let { dishes ->
                        items(dishes){dish ->
                            DishItemList(
                                dish = dish,
                                context = context
                            )
                        }
                    }
                }
            }
            else
            {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {

                    state.successfull?.let { dishes ->
                        items(dishes){dish ->
                            DishItem(
                                dish = dish,
                                context =context,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PagerHeaderHomeComponent(
    dish: Dish,
    onSelectedItem: (Dish) -> Unit
) {
    val context = LocalContext.current
    Box(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onSelectedItem(dish)
        }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(dish.thumbails).crossfade(1000)
                .build(),
            contentDescription = dish.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        ) {
            TextComponent(
                text = dish.name, style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
            SpacerComponent(modifier = Modifier.height(8.dp))
            TextComponent(
                text = dish.description, style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                )
            )
        }
    }
}

@Composable
fun DishItemList(
    modifier: Modifier = Modifier,
    dish: Dish,
    context: Context,
) {
    Card(
        border = BorderStroke(width = 1.dp, color = PrimaryButton),
        modifier = modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .height(120.dp)
                    .clip(RoundedCornerShape(16.dp)),
                model = ImageRequest.Builder(context)
                    .data(dish.image)
                    .crossfade(2000)
                    .build(),
                contentDescription = "Template Dish",
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
                    .padding(start = 10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextComponent(
                        text = dish.name,
                        style = TextStyle(
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.weight(2f)
                    )
                    Row(modifier = Modifier.weight(1f),horizontalArrangement = Arrangement.End)
                    {
                        IconButton(onClick = {
                        }) {
                            Icon(
                                imageVector = Icons.Outlined.BookmarkBorder,
                                contentDescription = "Favorite",
                                tint = PrimaryButton,
                            )
                        }
                    }
                }

                Row(modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    TextComponent(
                        text = "Carbohidratos : ",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal
                        )
                    )
                    TextComponent(
                        text = "${dish.carbohydrates}",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryButton
                        )
                    )
                }

                Row (modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    TextComponent(
                        text = "Precio : ",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal
                        )
                    )
                    TextComponent(
                        text = "$${dish.price}",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryButton
                        )
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 2.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    RatingBar(currentRating = dish.rating.toInt())
                }
            }
        }

    }
}

@Composable
fun DishItem(
    modifier: Modifier = Modifier,
    dish: Dish,
    context: Context,
) {

    Card(
        border = BorderStroke(width = 1.dp, color = PrimaryButton),
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(RoundedCornerShape(16.dp)),
                model = ImageRequest.Builder(context)
                    .data(dish.image)
                    .crossfade(2000)
                    .build(),
                contentDescription = "Template Dish",
                contentScale = ContentScale.Crop
            )
            SpacerComponent(modifier = Modifier.height(2.dp))
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {
                TextComponent(
                    text = dish.name,
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                IconButton(onClick = {
                }) {
                    Icon(
                        imageVector = if(dish.favorite) Icons.Filled.Bookmark
                        else Icons.Outlined.BookmarkBorder,
                        contentDescription = "Favorite",
                        tint = PrimaryButton,
                    )
                }
            }

            SpacerComponent(modifier = Modifier.height(2.dp))
            TextComponent(
                text = "Carbohidratos",
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal
                )
            )
            TextComponent(
                text = "${dish.carbohydrates}",
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryButton
                )
            )
            SpacerComponent(modifier = Modifier.height(8.dp))
            TextComponent(
                text = "Precio",
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal
                )
            )
            TextComponent(
                text = "$${dish.price}",
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryButton
                )
            )

            RatingBar(currentRating = dish.rating.toInt())
        }
    }
}

@Composable
fun RatingBar(
    maxRating: Int = 5,
    currentRating: Int,
    starsColor: Color = Color.Yellow
) {
    Row {
        for (i in 1..maxRating) {
            Icon(
                imageVector = if (i <= currentRating) Icons.Filled.Star
                else Icons.Filled.StarOutline,
                contentDescription = "Rating",
                tint = if (i <= currentRating) starsColor
                else Color.Unspecified,
                modifier = Modifier
                    .clickable { }
                    .padding(2.dp)
            )
        }
    }
}

@Preview (name = "DishScreen", showSystemUi = true)
@Composable
fun DishScreenPreview() {
    DishScreen(paddingValues = PaddingValues())
}