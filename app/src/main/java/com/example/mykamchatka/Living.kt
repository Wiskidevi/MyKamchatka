package com.example.mykamchatka

import kotlinx.serialization.Serializable


@Serializable
data class Living (
    val id: Int = 0,
    val name: String = "",
    val date: String = "",
    val price: String = "",
    val image: String = ""
)