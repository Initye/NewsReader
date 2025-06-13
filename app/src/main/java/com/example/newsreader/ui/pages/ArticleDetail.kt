package com.example.newsreader.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsreader.R
import com.example.newsreader.ui.ApiViewModel
import com.example.newsreader.ui.theme.getGeistFontFamily

@Composable
fun ArticleDetail(viewModel: ApiViewModel, modifier: Modifier = Modifier ) {
    val selectedArticle by viewModel.selectedArticle
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .safeContentPadding(),
    ) {
        selectedArticle?.let { article ->
            // Display your article details here
            Column {
                Text(text = article.title ?: "No title")
                Text(text = article.author ?: "No author")
                // etc.
            }
        } ?: run {
            // Handle case where no article is selected
            Text("No article selected")
        }
    }
}