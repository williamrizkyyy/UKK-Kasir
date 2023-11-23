package com.example.uklkasir

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.uklkasir.userdatabase.CafeDatabase
import com.example.uklkasir.userdatabase.User

class RegisterActivity : AppCompatActivity() {
    lateinit var editName: EditText
    lateinit var editEmail: EditText
    lateinit var editPassword: EditText
    lateinit var buttonSave: Button
    lateinit var pilihRole: Spinner

    lateinit var db: CafeDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        init()
        setDataSpinner()
        buttonSave.setOnClickListener{
            if(editName.text.toString().isNotEmpty() && editEmail.text.toString().isNotEmpty() && editPassword.text.toString().isNotEmpty() && pilihRole.selectedItem.toString() != "Pilih Role"){
                db.cafeDao().insertUser(User(
                    null,
                    editName.text.toString(),
                    editEmail.text.toString(),
                    editPassword.text.toString(),
                    pilihRole.selectedItem.toString())
                )
                Toast.makeText(applicationContext, "Register successful", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    fun init(){
        editName = findViewById(R.id.editName)
        editEmail = findViewById(R.id.editEmail)
        editPassword = findViewById(R.id.editPassword)
        buttonSave = findViewById(R.id.buttonSave)
        pilihRole = findViewById(R.id.spinnerRole)

        db = CafeDatabase.getInstance(applicationContext)
    }

    private fun setDataSpinner(){
        val adapter = ArrayAdapter.createFromResource(applicationContext, R.array.roles, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        pilihRole.adapter = adapter
    }
}