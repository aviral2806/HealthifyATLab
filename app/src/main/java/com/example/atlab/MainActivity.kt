package com.example.atlab

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val user: EditText = findViewById(R.id.username)
        val pas: EditText = findViewById(R.id.pass)
        val btn1 : Button = findViewById(R.id.btnReg)
        val btn2 : Button = findViewById(R.id.btnLogin)
        val pas2: EditText = findViewById(R.id.pass2)

        btn1.setOnClickListener {
            val uname = user.text.toString()
            val pass = pas.text.toString()
            val pass2 = pas2.text.toString()
            if(uname.equals("") || pass.equals("") || pass2.equals("") ){
                Toast.makeText(this,"Enter all fields!!",Toast.LENGTH_SHORT).show()
            }else{
                if(pass.equals(pass2)){
                    val db = DBHelper(this)
                    val dbr = db.readableDatabase
                    val crsr = dbr.rawQuery("select * from users where username = ?",
                        arrayOf(uname)
                    )
                    if(crsr.count > 0){
                        Toast.makeText(this,"User already exists",Toast.LENGTH_SHORT).show()

                    }else{
                        val dbw = db.writableDatabase
                        val cv= ContentValues()
                        cv.put("username",uname)
                        cv.put("password",pass)
                        val res = dbw.insert("users",null,cv)
                        Toast.makeText(this,"Data inserted $res",Toast.LENGTH_SHORT).show()
                    }
                    crsr.close()
                }else{
                    Toast.makeText(this,"Both passwords should match",Toast.LENGTH_SHORT).show()
                }
            }
        }

        btn2.setOnClickListener {
            val inte = Intent(this,LoginActivity::class.java)
            startActivity(inte)
        }

    }
}