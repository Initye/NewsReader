package com.example.newsreader.apiCalls

import com.example.newsreader.BuildConfig
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

private val client = OkHttpClient()


data class Source (
    val id: String?,
    val name: String,
)
data class Article (
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
)
data class NewsResponse (
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)
//serialization/deserialization library
var gson = Gson()

fun main() {
    run()
}

fun run() {
    val request = Request.Builder()
        .url("https://newsapi.org/v2/top-headlines?country=us&apiKey=${BuildConfig.API_KEY}")
        .build()

    client.newCall(request).execute().use { response ->
        if(!response.isSuccessful) throw IOException("Unexpected code $response")

        val result = response.body?.string()
        //Converting json into class
        var newsResponse = gson.fromJson(result, NewsResponse::class.java)

        val firstArticleTitle = newsResponse.articles.firstOrNull()?.title ?: "No data"

        println(firstArticleTitle)

        newsResponse.articles.take(5).forEach { article ->
            //Elvis operator does not work on (val title = article.title ?:) since it checks the values not the empty strings that's why isNotBlank is used
            val title = article.title.takeIf { it.isNotBlank() } ?: "Unknown title"
            val author = article.author.takeIf { it.isNotBlank() } ?: "Unknown author"
            println("Title of article: ${title} \nAuthor of article: ${author}")
        }
    }
}