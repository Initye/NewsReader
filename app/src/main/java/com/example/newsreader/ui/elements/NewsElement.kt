package com.example.newsreader.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsreader.R
import androidx.compose.ui.unit.sp
import com.example.newsreader.ui.theme.getGeistFontFamily


@Composable
fun NewsElement(modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(132.dp)
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.s1e13_yin_profile),
                contentDescription = ""
            )
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 20.dp, bottom = 5.dp)
            ) {
                Text(
                    text = "Animals get boost from tree vandals",
                    color = Color.Black,
                    fontFamily = getGeistFontFamily(), fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = "19 october 2025",
                    fontSize = 10.sp,
                    fontFamily = getGeistFontFamily(), fontWeight = FontWeight.Normal
                )
            }
        }
    }
}

@Preview
@Composable
fun NewsElementPreview() {
    NewsElement()
}