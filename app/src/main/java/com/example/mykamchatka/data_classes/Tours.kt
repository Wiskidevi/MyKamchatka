package com.example.mykamchatka.data_classes

import android.os.Parcelable
import kotlinx.serialization.Serializable
import kotlinx.parcelize.Parcelize

@Parcelize
@Serializable
data class Tours (
    val name: String = "",
    val date: String = "",
    val duration: String = "",
    val price: String = "",
    val imageUrl: String = "",
    val things_to_bag: String = "",
) : Parcelable