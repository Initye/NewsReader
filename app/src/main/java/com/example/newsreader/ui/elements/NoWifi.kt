package com.example.newsreader.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsreader.R
import com.example.newsreader.ui.ApiViewModel
import com.example.newsreader.ui.theme.getGeistFontFamily
import androidx.compose.foundation.layout.fillMaxSize

@Composable
fun NoWifi(modifier: Modifier = Modifier) {
    //Getting data from apiCall
    val viewModel: ApiViewModel = viewModel()

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                text = "Couldn't get the data, please check your internet connection",
                color = Color.Black,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                fontFamily = getGeistFontFamily(), fontWeight = FontWeight.Normal,
            )
            IconButton(
                modifier = modifier
                    .align(Alignment.CenterHorizontally),
                onClick = { viewModel.retryLoadingArticle() },
                content = {
                    Image(
                        painter = painterResource(R.drawable.refresh_button),
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(Color.Black)
                    )
                }
            )
        }
    }
}
