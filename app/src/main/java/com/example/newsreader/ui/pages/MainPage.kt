package com.example.newsreader.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsreader.ui.ApiViewModel
import com.example.newsreader.ui.elements.Header
import com.example.newsreader.ui.elements.HeadlineElement
import com.example.newsreader.ui.elements.LatestElement
import com.example.newsreader.ui.elements.LatestHeadlineElement
import com.example.newsreader.ui.elements.NewsElement
import com.example.newsreader.R
import com.example.newsreader.ui.elements.NoWifi
import com.example.newsreader.ui.theme.Headlines
import com.example.newsreader.ui.theme.geistFontFamily
import com.example.newsreader.ui.theme.getGeistFontFamily


@Composable
fun MainPage(modifier: Modifier = Modifier) {
    //Getting data from apiCall
    val viewModel: ApiViewModel = viewModel()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .safeContentPadding(),
    ) {
        Column {
            Header()
            if(viewModel.networkError.value == false) {
                Box (
                    modifier = modifier
                        .padding(start = 16.dp, top = 20.dp, end = 16.dp)
                ) {
                    Column {
                        LatestHeadlineElement()
                        LatestElement()
                        Spacer(modifier = Modifier.weight(1f))
                        HeadlineElement()
                        NewsElement()
                    }
                }
            } else {
                NoWifi()
            }
        }
    }
}


@Preview
@Composable
fun PagePreview() {
    MainPage()
}