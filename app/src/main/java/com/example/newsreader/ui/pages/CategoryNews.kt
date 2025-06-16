package com.example.newsreader.ui.pages

import android.util.Log
import android.util.Log.e
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.newsreader.ui.ApiViewModel
import com.example.newsreader.ui.elements.NewsElement
import androidx.compose.runtime.getValue

@Composable
fun CategoryNews(navController: NavController, viewModel: ApiViewModel = viewModel()) {
    val category by viewModel.selectedCategory
    LaunchedEffect(Unit) {
        viewModel.onCategory(category)
        Log.e("Selected category", category)
    }
    NewsElement(navController = navController, viewModel = viewModel)
}


@Preview
@Composable
fun BitcoinNewsPreview() {
    CategoryNews(navController = TODO(), viewModel = viewModel())
    NewsElement( navController = TODO(), viewModel = viewModel())
}