package com.example.newsreader.ui.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.newsreader.ui.ApiViewModel
import com.example.newsreader.ui.theme.Headlines
import com.example.newsreader.ui.theme.getGeistFontFamily
import com.example.newsreader.R

@Composable
fun LatestHeadlineElement(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.latest),
        color = Headlines,
        fontSize = 36.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun LatestElement(modifier: Modifier = Modifier) {
    val viewModel: ApiViewModel = viewModel()
    val articles by viewModel.latestArticle
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.cardBackground)
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(132.dp)
            .padding(bottom = 8.dp)
    ) {
        Box {
            AsyncImage(
                model = articles?.urlToImage ?: R.drawable.noimage_error,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .fillMaxSize()
            )
            Text(
                text = articles?.title ?: "",
                color = Color.White,
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Black,
                        offset = Offset(2f, 2f),
                        blurRadius = 4f
                    )
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 14.sp,
                fontFamily = getGeistFontFamily(), fontWeight = FontWeight.SemiBold,
                modifier = modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            )
        }
    }
}


@Preview
@Composable
fun LatestElementPreview() {
    LatestHeadlineElement()
}

@Preview
@Composable
fun LatestPreview() {
    LatestElement()
}
