package com.example.mykamchatka.supabase

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest


class SupabaseHelper {

    fun getClient(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = "https://dghywtgmjhxooohbuthl.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImRnaHl3dGdtamh4b29vaGJ1dGhsIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjgzNDMxNjMsImV4cCI6MjA0MzkxOTE2M30.-lsBVoa8WnzFsRWTGi8oDYEqAP7KtSs3brt9jW_uKk0"
        ) {
            install(Postgrest)
        }
    }


    suspend inline fun <reified T : Any> getData(tableName: String) : List<T> {
        val client = getClient()
        val supabaseResponse = client.postgrest[tableName].select()
        return supabaseResponse.decodeList<T>()
    }

}