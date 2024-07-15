package com.rowdy.shop_inventory.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.rowdy.shop_inventory.R
import com.rowdy.shop_inventory.ui.theme.Shop_inventoryTheme
import kotlinx.coroutines.delay
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
        Shop_inventoryTheme {
            // A surface container using the 'background' color from the theme
            val layoutDirection = LocalLayoutDirection.current
            Surface(
                modifier = Modifier
                    .padding(
                        start = WindowInsets.safeDrawing.asPaddingValues()
                            .calculateStartPadding(layoutDirection),
                        end = WindowInsets.safeDrawing.asPaddingValues()
                            .calculateEndPadding(layoutDirection)
                    )
            ) {
                MainView()
            }
        }
    }
}
}


@OptIn( ExperimentalPagerApi::class)
@Composable
fun MainView() {
    val pagerState = rememberPagerState()
    val imageList = listOf(R.drawable.image1, R.drawable.image2, R.drawable.image3,R.drawable.image4,R.drawable.image5) // Replace with your image resources

    LaunchedEffect(Unit) {
        while (true) {
            delay(2500) // Delay of 3 seconds between image changes
            pagerState.animateScrollToPage((pagerState.currentPage + 1) % imageList.size)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            count = imageList.size,
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Image(
                painter = painterResource(id = imageList[page]),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        )
        Button(
            onClick = {
          //  MainScreen(context = this)
               // Replace "mainScreen" with your actual destination
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        ) {
            Text(text = "Skip")
        }
    }
}

@Composable
fun MainScreen(context:Context) {
    val intent = Intent(context, MainActivity::class.java)
    context.startActivity(intent)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewForSplash() {
    Shop_inventoryTheme {
        MainView()
    }
}