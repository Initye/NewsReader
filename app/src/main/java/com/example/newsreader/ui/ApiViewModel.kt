package com.example.newsreader.ui

import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log.e
import androidx.compose.runtime.MutableState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import java.util.Date

class ApiViewModel : ViewModel() {
    init {
       viewModelScope.launch{
           try {
               val result = ApiCallList()
               _articles.value = result
               _latestArticle.value = result.firstOrNull()
               _networkError.value = false
           } catch (e: IOException) {
               _networkError.value = true
               println("Network error is occurring")
               println("Network error: ${e.message}")
           }
       }
    }
    private val _networkError = mutableStateOf(false)
    val networkError: State<Boolean> = _networkError

    fun retryLoadingArticle() {
        viewModelScope.launch{
            try {
                val result = ApiCallList()
                _articles.value = result
                _latestArticle.value = result.firstOrNull()
                _networkError.value = false
            } catch (e: IOException) {
                _networkError.value = true
                println("Network error: ${e.message}")
            }
        }
    }

    data class Source (
        val id: String?,
        val name: String?,
    )
    data class Article (
        val source: Source,
        val author: String?,
        val title: String?,
        val description: String?,
        val publishedAt: String?,
        val urlToImage: String?,
        val content: String?
    )
    data class NewsResponse (
        val status: String,
        val totalResults: Int,
        //Creates list from Article data class
        val articles: List<Article>
    )
    //Gson - serialization/deserialization library
    var gson = Gson()

    //To call
    private val _articles = mutableStateOf<List<Article>>(emptyList())
    val articles: State<List<Article>> = _articles
    private val _latestArticle = mutableStateOf<Article?>(null)
    val latestArticle: State<Article?> = _latestArticle

    suspend fun ApiCallList(
        useEverything: Boolean = false,
        query: String? = null
    ): List<Article> {
        val client = OkHttpClient()
        val baseUrl = if(useEverything) {
            "https://newsapi.org/v2/everything?q=${query}"
        } else {
            "https://newsapi.org/v2/top-headlines?country=us"
        }
        val request = Request.Builder()
            .url("$baseUrl&apiKey=${BuildConfig.API_KEY}")
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

    //Categories
    fun onEverything() {
        viewModelScope.launch{
            try {
                val result = ApiCallList(useEverything = false)
                _articles.value = result
                _latestArticle.value = result.firstOrNull()
                _networkError.value = false
            } catch (e: IOException) {
                _networkError.value = true
            }
        }
    }
    fun onBitcoin() {
        viewModelScope.launch{
            try {
                val result = ApiCallList(useEverything = true, query = "bitcoin")
                _articles.value = result
                _latestArticle.value = result.firstOrNull()
                _networkError.value = false
            } catch (e: IOException) {
                _networkError.value = true
            }
        }
    }

    //Selecting article
    private val _selectedArticle = mutableStateOf<Article?>(null)
    val selectedArticle: State<Article?> = _selectedArticle

    fun selectArticle(article: Article) {
        _selectedArticle.value = article
    }

}