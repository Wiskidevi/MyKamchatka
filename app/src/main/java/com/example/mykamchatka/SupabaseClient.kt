package com.example.mykamchatka

import android.util.Log
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.serialization.Serializable


@Serializable
data class News (
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val date: String = "",
    val imageUrl: String = "",
    val article: String = "",
    val site: String = ""
)



class SupabaseClient {
    private val client = createSupabaseClient(
        supabaseUrl = "https://dghywtgmjhxooohbuthl.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImRnaHl3dGdtamh4b29vaGJ1dGhsIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjgzNDMxNjMsImV4cCI6MjA0MzkxOTE2M30.-lsBVoa8WnzFsRWTGi8oDYEqAP7KtSs3brt9jW_uKk0"
    ) {
        install(Postgrest)
    }

//    suspend fun fetchNews(): List<News> {
//        return try {
//            // Тестовый запрос без десериализации для проверки работы запроса
//            val response = client.postgrest["NewsTable"].select()
//            Log.d("Supabase raw response", response.toString()) // Выводим для отладки
//
//            // Пытаемся десериализовать
//            response.decodeList<News>()
//        } catch (e: Exception) {
//            Log.e("Supabase Error fetch", "Error fetching data: ${e.localizedMessage}")
//            emptyList()
//        }
//    }

    suspend fun fetchNews(): List<News> {
        return try {
            client.postgrest["NewsTable"].select().decodeList<News>()
        } catch (e: Exception) {
            Log.e("Supabase Error", "Error fetching data: ${e.localizedMessage}")
            emptyList()
        }
    }


    suspend fun addNewsItem(newsItem: News) {
        try {
            client.postgrest["NewsTable"].insert(newsItem)
        } catch (e: Exception) {
            Log.e("Supabase Error add", "Error adding news item: ${e.localizedMessage}")
        }
    }
}
