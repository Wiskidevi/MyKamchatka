package com.example.mykamchatka

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext



class NewsViewModel : ViewModel() {
    private val supabaseClient = SupabaseClient()

    private val _newsList = MutableLiveData<List<News>>()
    val newsList: LiveData<List<News>> get() = _newsList

    fun fetchNews() {
        viewModelScope.launch {
            val news = withContext(Dispatchers.IO) {
                supabaseClient.fetchNews()
            }
            _newsList.value = news
        }
    }

    fun addNewsItem(newsItem: News) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                supabaseClient.addNewsItem(newsItem)
            }
            fetchNews() // Обновление списка после добавления новой новости
        }
    }
}

