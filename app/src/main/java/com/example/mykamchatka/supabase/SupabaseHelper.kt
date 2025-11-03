package com.example.mykamchatka.supabase

import android.content.Context
import android.net.Uri
import android.util.Log
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.OtpType
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.storage.Storage
import io.github.jan.supabase.storage.storage
import io.github.jan.supabase.storage.upload
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream


class SupabaseHelper() {

    fun getClient(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = "https://dghywtgmjhxooohbuthl.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImRnaHl3dGdtamh4b29vaGJ1dGhsIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjgzNDMxNjMsImV4cCI6MjA0MzkxOTE2M30.-lsBVoa8WnzFsRWTGi8oDYEqAP7KtSs3brt9jW_uKk0"
        ) {
            install(Postgrest)
            install(io.github.jan.supabase.storage.Storage)
        }
    }

    suspend fun uploadImage(bucketName: String, fileName: String, fileData: ByteArray): String {
        val client = getClient()
        try {
            val bucket = client.storage[bucketName]
            bucket.upload("public/$fileName", fileData)
            val projectUrl = "https://dghywtgmjhxooohbuthl.supabase.co"
            return "$projectUrl/storage/v1/object/public/$bucketName/public/$fileName"
        } catch (e: Exception) {
            Log.e("SupabaseHelper", "Error uploading image to bucket: $bucketName", e)
            throw e
        }
    }


    suspend inline fun <reified T : Any> getData(tableName: String) : List<T> {
        val client = getClient()
        val supabaseResponse = client.postgrest[tableName].select()
        return supabaseResponse.decodeList<T>()
    }

    suspend inline fun <reified T : Any> setData(tableName: String, data : List<T>) {
        val client = getClient()
        client.from(tableName).insert(data)
    }

    suspend fun addUser(emailU: String, passwordU: String): String {
        val client = getClient()
        val user = client.auth.signUpWith(Email){
            email = emailU
            password = passwordU
        }
        return user!!.id
    }



}