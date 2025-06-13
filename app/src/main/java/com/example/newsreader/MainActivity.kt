package com.example.newsreader

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsreader.ui.ApiViewModel
import com.example.newsreader.ui.pages.ArticleDetail
import com.example.newsreader.ui.pages.MainPage


import com.example.newsreader.ui.theme.NewsReaderTheme
import com.example.newsreader.ui.theme.geistFontFamily

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val sharedProfileViewModel: ApiViewModel = viewModel()
            NewsReaderTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    NavHost(
                        navController = navController,
                        startDestination = "mainPage",
                    ) {
                        composable("mainPage") {
                            MainPage(navController, sharedProfileViewModel)
                        }
                        composable("articleElement") {
                            ArticleDetail(sharedProfileViewModel)
                        }
                    }
                }
            }
        }
    }
}

