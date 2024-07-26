package com.example.sqlite_practice.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.sqlite_practice.User


class DbHelper(val content: Context, val factory: SQLiteDatabase.CursorFactory?):
    SQLiteOpenHelper(content, "app", factory, 1){

        companion object {
             const val USERS_VK = "usersVk"
        }


    override fun onCreate(db: SQLiteDatabase?) {
        val quary = "CREATE TABLE USERS_VK (id INT PRIMARY KEY, name TEXT, surname TEXT)"
        db!!.execSQL(quary)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS USERS_VK")
        onCreate(db)
    }

    fun addUser(user: User) {
        val values = ContentValues()
        values.put("name", user.name)
        values.put("surname", user.surname)

        val db = this.writableDatabase
        db.insert("USERS_VK", null, values)

        db.close()
    }

    fun getUser(name: String, surname: String): Boolean {
        val db = this.readableDatabase

        val result = db.rawQuery("SELECT * FROM USERS_VK WHERE name = '$name' AND surname = '$surname'", null)
        return result.moveToFirst()
    }

}