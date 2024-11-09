package com.example.mykamchatka.supabase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DbHelper(val context: Context, val factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "app", factory, 3) {

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE users (id INT PRIMARY KEY, email TEXT, pass TEXT, surname TEXT, name TEXT, thirdname TEXT, birthday TEXT, role TEXT)"
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }

    fun addUser(user: User){
        val values = ContentValues()
        values.put("email", user.email)
        values.put("pass", user.pass)
        values.put("surname", user.surname)
        values.put("name", user.name)
        values.put("thirdname", user.thirdname)
        values.put("birthday", user.birthday)
        values.put("role", user.role)

        val db = this.writableDatabase
        db.insert("users", null, values)

        db.close()
    }

}