package com.example.newsreader.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.newsreader.ui.ApiViewModel
import com.example.newsreader.ui.elements.Header
import com.example.newsreader.ui.elements.HeadlineElement
import com.example.newsreader.ui.elements.LatestElement
import com.example.newsreader.ui.elements.LatestHeadlineElement
import com.example.newsreader.ui.elements.NavDrawer
import com.example.newsreader.ui.elements.NewsElement
import com.example.newsreader.ui.elements.NoWifi


@Composable
fun MainPage(navController: NavController, viewModel: ApiViewModel, modifier: Modifier = Modifier) {
    LaunchedEffect(Unit) {
        viewModel.onEverything()
    }
    NavDrawer(navController, viewModel = viewModel) { onMenuClick ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
                .safeContentPadding(), //WindowInsets.SystemBars
        ) {
            Column {
                Header(
                    onMenuClick = onMenuClick
                )
                if(viewModel.networkError.value == false) {
                    Box (
                        modifier = modifier
                            .padding(start = 16.dp, top = 20.dp, end = 16.dp)
                    ) {
                        Column {
                            LatestHeadlineElement()
                            LatestElement(navController, viewModel = viewModel)
                            Spacer(modifier = Modifier.weight(1f))
                            HeadlineElement()
                            NewsElement(navController, viewModel = viewModel)
                        }
                    }
                } else {
                    NoWifi()
                }
            }
        }
    }
}


@Preview
@Composable
fun PagePreview() {
    MainPage(navController = TODO(), viewModel = viewModel())
}