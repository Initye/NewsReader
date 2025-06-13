package com.example.newsreader.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.newsreader.R
import com.example.newsreader.ui.ApiViewModel
import com.example.newsreader.ui.theme.Headlines
import com.example.newsreader.ui.theme.getGeistFontFamily


@Composable
fun HeadlineElement(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.headline),
        color = Headlines,
        fontSize = 36.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun NewsElement(modifier: Modifier = Modifier) {
    //Getting data from apiCall
    val viewModel: ApiViewModel = viewModel()
    val articles by viewModel.articles

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        items(articles) { article ->
            val date = article.publishedAt?.substringBefore("T")
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(R.color.cardBackground)
                ),
                modifier = modifier
                    .fillMaxWidth()
                    .height(132.dp)
                    .padding(bottom = 8.dp)
            ) {
                Row {
                    AsyncImage(
                        model = article.urlToImage ?: R.drawable.block_error,
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .size(130.dp)
                    )
                    Column(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 4.dp)
                    ) {
                        Text(
                            text = article.title ?: stringResource(R.string.no_data),
                            color = Color.Black,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis, //Three dots
                            lineHeight = 16.sp,
                            fontSize = 16.sp,
                            fontFamily = getGeistFontFamily(), fontWeight = FontWeight.SemiBold
                        )
                        Spacer(Modifier.weight(1f))
                        Row {
                            Text(
                                text = date ?: stringResource(R.string.no_data),
                                color = Color.Black,
                                fontSize = 8.sp,
                                fontFamily = getGeistFontFamily(), fontWeight = FontWeight.Normal
                            )
                            Text (
                                text = " • ",
                                color = Color.Black,
                                fontSize = 8.sp
                            )
                            Text(
                                text = article.author ?: stringResource(R.string.no_data),
                                color = Color.Black,
                                fontSize = 8.sp,
                                fontFamily = getGeistFontFamily(), fontWeight = FontWeight.Normal
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun NewsElementPreview() {
    NewsElement(
    )
}