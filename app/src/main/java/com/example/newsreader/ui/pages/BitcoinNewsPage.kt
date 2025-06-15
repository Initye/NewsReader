package com.example.newsreader.ui.pages

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.newsreader.ui.ApiViewModel
import com.example.newsreader.ui.elements.NewsElement

@Composable
fun BitcoinNews(navController: NavController, viewModel: ApiViewModel) {
    NewsElement(navController, viewModel = viewModel)

}

@Preview
@Composable
fun BitcoinNewsPreview() {
    BitcoinNews(navController = TODO(), viewModel = viewModel())
    NewsElement( navController = TODO(), viewModel = viewModel())
}