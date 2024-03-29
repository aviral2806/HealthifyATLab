package com.example.atlab

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context,DB_NAME,null,
    DB_VER)  {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table users(username TEXT primary key,password TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists users")
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
    }

    public fun insertData(user: String,pass: String) : Boolean{
        val db = this.writableDatabase
        val cv= ContentValues()
        cv.put("username",user)
        cv.put("password",pass)
        val res = db.insert("users",null,cv)
        if(res == (-1).toLong()) return false
        else return true
    }



companion object{
    private const val DB_NAME = "REGISTER.db"
    private const val DB_VER = 1
}

}

