package com.example.newsreader.ui.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.newsreader.ui.ApiViewModel
import com.example.newsreader.ui.elements.NewsElement

@Composable
fun BitcoinNews(navController: NavController, viewModel: ApiViewModel = viewModel()) {
    LaunchedEffect(Unit) {
        viewModel.onBitcoin()
    }
    NewsElement(navController = navController, viewModel = viewModel)
}


@Preview
@Composable
fun BitcoinNewsPreview() {
    BitcoinNews(navController = TODO(), viewModel = viewModel())
    NewsElement( navController = TODO(), viewModel = viewModel())
}