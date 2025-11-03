package com.example.mykamchatka.supabase

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val uuid: String,
    val email: String?,
    val pass: String?,
    val surname: String?,
    val name: String?,
    val thirdname: String?,
    val birthday: String?,
    val role: String?
)