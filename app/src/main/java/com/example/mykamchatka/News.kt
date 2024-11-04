package com.example.mykamchatka

import kotlinx.serialization.Serializable

@Serializable
data class News (
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val date: String = "",
    val imageUrl: String = "",
    val article: String = "",
    val site: String = "",
    val news_type: String = "",
)