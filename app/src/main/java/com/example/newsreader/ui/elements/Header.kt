package com.example.newsreader.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsreader.ui.theme.Headlines
import com.example.newsreader.ui.theme.getGeistFontFamily

@Composable
fun Header(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            //Shadow only on bottom
            .drawBehind {
                drawRect(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.2f),
                            Color.Transparent
                        ),
                        startY = size.height,
                        endY = size.height + 6.dp.toPx()
                    ),
                    topLeft = Offset(0f, size.height),
                    size = Size(size.width, 6.dp.toPx())
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "NewsReader",
            fontFamily = getGeistFontFamily(),
            fontWeight = FontWeight.Bold,
            color = Headlines,
            fontSize = 34.sp
        )
    }
}
