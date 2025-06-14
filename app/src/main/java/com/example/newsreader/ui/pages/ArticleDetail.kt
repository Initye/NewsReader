package com.example.newsreader.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.newsreader.R
import com.example.newsreader.ui.ApiViewModel
import com.example.newsreader.ui.elements.Header
import com.example.newsreader.ui.theme.getGeistFontFamily

@Composable
fun ArticleDetail(viewModel: ApiViewModel, modifier: Modifier = Modifier  ) {
    val selectedArticle by viewModel.selectedArticle
    val sourceAuthorTextStyle = TextStyle (
        color = Color.Black,
        fontSize = 12.sp,
        fontFamily = getGeistFontFamily(), fontWeight = FontWeight.Normal
    )
    val titleTextStyle = TextStyle (
        color = Color.Black,
        fontSize = 20.sp,
        fontFamily = getGeistFontFamily(), fontWeight = FontWeight.Bold
    )
    val descriptionTextStyle = TextStyle (
        color = Color.Black,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontFamily = getGeistFontFamily(), fontWeight = FontWeight.Normal
    )
    Column {
        Header()
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
                .safeContentPadding(),
        ) {
            selectedArticle?.let { article ->
                Column {
                    AsyncImage(
                        model = article.urlToImage,
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                        modifier = modifier
                            .fillMaxWidth()
                            .heightIn(max = 400.dp)
                    )
                    Column(
                        modifier = modifier
                            .padding(8.dp)
                    ) {
                        Row {
                            Text(
                                text = article.source.name
                                    ?: stringResource(id = R.string.no_source),
                                style = sourceAuthorTextStyle
                            )
                            Spacer(modifier.weight(1f))
                            Text(
                                text = "By ${article.author ?: stringResource(id = R.string.no_author)}",
                                style = sourceAuthorTextStyle
                            )
                        }
                        Text(
                            text = article.title ?: stringResource(id = R.string.no_title),
                            style = titleTextStyle
                        )
                        Text(
                            text = article.content
                                ?: stringResource(id = R.string.no_content),
                            style = descriptionTextStyle
                        )
                    }
                }
            } ?: run {
                Text("No article selected")
            }
        }
    }
}

@Composable
@Preview
fun ArticleDetailPreview() {
    ArticleDetail(viewModel = viewModel())
}