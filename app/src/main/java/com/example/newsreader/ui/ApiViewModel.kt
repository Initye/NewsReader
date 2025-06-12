package com.example.newsreader.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsreader.BuildConfig
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import androidx.compose.runtime.State
import java.util.Date

class ApiViewModel : ViewModel() {
    init {
        viewModelScope.launch{
            val result = ApiCallList()
            _articles.value = result
        }
    }

    data class Source (
        val id: String?,
        val name: String,
    )
    data class Article (
        val source: Source,
        val author: String,
        val title: String,
        val description: String,
        val publishedAt: String,
    )
    data class NewsResponse (
        val status: String,
        val totalResults: Int,
        val articles: List<Article>
    )
    //Gson - serialization/deserialization library
    var gson = Gson()

    private val _articles = mutableStateOf<List<Article>>(emptyList())
    val articles: State<List<Article>> = _articles
    suspend fun ApiCallList(): List<Article> {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://newsapi.org/v2/top-headlines?country=us&apiKey=${BuildConfig.API_KEY}")
            .build()

        return withContext(Dispatchers.IO) {
            client.newCall(request).execute().use { response ->
                if(!response.isSuccessful) throw IOException("Unexpected code $response")

                val result = response.body?.string()
                //Converting json into class
                var newsResponse = gson.fromJson(result, NewsResponse::class.java)

                val list = mutableListOf<Article>()

                newsResponse.articles.take(5).forEach { article ->
                    list.add(article)
                }
                list //returned
            }
        }
    }
}