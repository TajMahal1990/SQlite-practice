package com.example.sqlite_practice

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sqlite_practice.databinding.ActivityMainBinding
import com.example.sqlite_practice.db.DbHelper

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnToAuthFromReg.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }

        binding.btnReg.setOnClickListener {
            val userName = binding.editNameReg.text.toString().trim()
            val userSurName = binding.editSurNameReg.text.toString().trim()

            if(userName == "" || userSurName == "") {
                Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_SHORT).show()
            } else {
                val user = User(userName, userSurName)

                val db = DbHelper(this, null)
                db.addUser(user)
                Toast.makeText(this, "Пользователь с именем $userName авторизован", Toast.LENGTH_SHORT).show()

                binding.editNameReg.text.clear()
                binding.editSurNameReg.text.clear()

                val intent = Intent(this, AuthActivity::class.java)
                startActivity(intent)

            }
        }
    }
}