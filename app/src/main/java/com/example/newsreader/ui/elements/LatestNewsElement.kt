package com.example.newsreader.ui.elements

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.newsreader.ui.theme.Headlines

@Composable
fun LatestHeadlineElement(modifier: Modifier = Modifier) {
    Text(
        text = "Latest",
        color = Headlines,
        fontSize = 36.sp,
        fontWeight = FontWeight.Bold
    )
}