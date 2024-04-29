package com.mitocode.ecoats.presentation.payments

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mitocode.ecoats.domain.model.Payment
import com.mitocode.ecoats.presentation.common.TextComponent
import com.mitocode.ecoats.ui.theme.PrimaryButton

@Composable
fun PaymentScreen(
    viewmodel :PaymentViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
    idUser : Int
) {

    val state = viewmodel.state

    LaunchedEffect(key1 = true) {
        viewmodel.getPayments(idUser)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {

        Column {
            TextComponent(
                text = "Mis Pagos",
                style = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 8.dp)
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
                        text = "${state.error}",
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
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {

                    state.successfull?.let { PaymentItems ->
                        items(PaymentItems){payment ->
                            PaymentItem(payment = payment)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PaymentItem(payment: Payment)
{
    Card(
        border = BorderStroke(
            width = 1.dp,
            color = PrimaryButton
        ), modifier = Modifier.fillMaxWidth()
    )
    {

        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp))
        {
            Column {
                TextComponent(
                    text = "BOLETA - ${payment.id}",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.DarkGray
                    ), modifier = Modifier.fillMaxWidth()
                )
                Row (modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)){
                    TextComponent(
                        text = "Fecha",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        ), modifier = Modifier.weight(1f)
                    )
                    TextComponent(
                        text = "Hora",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        ), modifier = Modifier.weight(1f)
                    )
                    TextComponent(
                        text = "Total",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        ), modifier = Modifier.weight(1f)
                    )
                }

                Row (modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)){
                    TextComponent(
                        text = "${payment.fecha}",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal
                        ), modifier = Modifier.weight(1f)
                    )
                    TextComponent(
                        text = "${payment.hora}",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal
                        ), modifier = Modifier.weight(1f)
                    )
                    TextComponent(
                        text = "$${payment.total}",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryButton
                        ), modifier = Modifier.weight(1f)
                    )
                }
            }
        }

    }
}

@Preview(name = "CartShoppingScreen", showSystemUi = true)
@Composable
fun CartShoppingScreenPreview() {
    PaymentScreen(paddingValues = PaddingValues(), idUser = 0)
}