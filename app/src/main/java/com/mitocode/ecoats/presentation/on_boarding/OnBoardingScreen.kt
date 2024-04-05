package com.mitocode.ecoats.presentation.on_boarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.mitocode.ecoats.R
import com.mitocode.ecoats.common.ImageComponent
import com.mitocode.ecoats.common.TextComponent
import com.mitocode.ecoats.ui.theme.PrimaryButton
import com.mitocode.ecoats.ui.theme.PrimaryText

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(
    onClick: () -> Unit
) {

    val pagerState = rememberPagerState()

    val pages = listOf(
        OnBoardingPage(
            image = R.drawable.image01,
            title = "Explorer",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy"
        ),
        OnBoardingPage(
            image = R.drawable.image02,
            title = "Discover",
            description = "essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing"
        ),
        OnBoardingPage(
            image = R.drawable.image03,
            title = "Power",
            description = "popular belief, Lorem Ipsum is not simply random text. It has roots in a piece"
        )
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        HorizontalPager(
            count = pages.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(10f)
        ) { index ->
            PagerOnBoarding(pages[index])
        }
        Row(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            PagerFooter(pages, pagerState)
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            FinishButtonComponent(
                pagerState = pagerState,
                pages = pages,
                onClick = {
                    onClick()
                }
            )
        }


    }

}

@Composable
fun PagerOnBoarding(onBoardingPage: OnBoardingPage) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        ImageComponent(
            image = onBoardingPage.image,
            description = "Image 01",
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.7f)
        )
        TextComponent(
            text = onBoardingPage.title,
            style = TextStyle(
                color = PrimaryButton,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 24.sp
            )
        )
        TextComponent(
            text = onBoardingPage.description,
            style = TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                color = PrimaryText
            ),
            modifier = Modifier
                .padding(top = 32.dp)
                .padding(horizontal = 24.dp)
        )

    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PagerFooter(pages: List<OnBoardingPage>, pagerState: PagerState) {

    repeat(pages.size) { iteration ->
        val color = if (pagerState.currentPage == iteration) PrimaryButton else Color.LightGray
        Box(
            modifier = Modifier
                .padding(2.dp)
                .clip(CircleShape)
                .background(color)
                .size(12.dp)
        )
    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FinishButtonComponent(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    pages: List<OnBoardingPage>,
    onClick:()->Unit
) {
    AnimatedVisibility(
        visible = pagerState.currentPage == pages.size - 1
    ) {
        Button(
            modifier = modifier,
            onClick = { onClick() },
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryButton,
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Empecemos"
            )
        }
    }

}

@Preview(name = "Onboarding", showSystemUi = true)
@Composable
fun OnBoardingScreenPreview() {
    OnBoardingScreen(onClick = {})
}