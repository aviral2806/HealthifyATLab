package com.example.atlab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val etUser : EditText = findViewById(R.id.user)
        val etPas : EditText = findViewById(R.id.pass)
        val btn : Button = findViewById(R.id.btn1)
        btn.setOnClickListener {
            val uname = etUser.text.toString()
            val pass = etPas.text.toString()
            val db = DBHelper(this)
            val dbr = db.readableDatabase
            val crsr = dbr.rawQuery("select * from users where username=? and password=?",arrayOf<String>(uname,pass))
            if(crsr.count==0){
                Toast.makeText(this,"User doesn't exist pls register instead",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Login successful",Toast.LENGTH_SHORT).show()

            }
        }
    }
}