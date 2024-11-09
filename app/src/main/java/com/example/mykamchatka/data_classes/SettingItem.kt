package com.example.mykamchatka.data_classes

data class SettingItem(
    val iconResId: Int,
    val title: String,
    val onClick: (() -> Unit)? = null // Lambda для обработки клика
)