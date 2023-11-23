package com.example.uklkasir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.uklkasir.userdatabase.CafeDatabase
import com.example.uklkasir.userdatabase.User

class LoginActivity : AppCompatActivity() {
    lateinit var editEmail: EditText
    lateinit var editPassword: EditText
    lateinit var loginButton: Button
    lateinit var registerButon: Button

    lateinit var db: CafeDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        loginButton.setOnClickListener{
            if(editEmail.text.toString().isNotEmpty() && editPassword.text.toString().isNotEmpty()){
                var list: List<User> = db.cafeDao().login(editEmail.text.toString(), editPassword.text.toString())
                if(list.size > 0){
                    val name = list[0].nama
                    val role = list[0].role
                    val id_user = list[0].id_user
                    if(role == "Manager"){
                        val moveIntent = Intent(this@LoginActivity, ManagerActivity::class.java)
                        moveIntent.putExtra("name", name)
                        moveIntent.putExtra("role", role)
                        moveIntent.putExtra("id_user", id_user)
                        startActivity(moveIntent)
                    }
                    else{
                        val moveIntent = Intent(this@LoginActivity, MainActivity2::class.java)
                        moveIntent.putExtra("name", name)
                        moveIntent.putExtra("role", role)
                        moveIntent.putExtra("id_user", id_user)
                        startActivity(moveIntent)
                    }
                }
                else{
                    Toast.makeText(applicationContext, "User not found", Toast.LENGTH_SHORT).show()
                }
            }
        }
        registerButon.setOnClickListener{
            val moveIntent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(moveIntent)
        }
    }

    fun init(){
        editEmail = findViewById(R.id.editEmail)
        editPassword = findViewById(R.id.editPassword)
        loginButton = findViewById(R.id.buttonLogin)
        registerButon = findViewById(R.id.buttonRegister)

        db = CafeDatabase.getInstance(applicationContext)
    }
}