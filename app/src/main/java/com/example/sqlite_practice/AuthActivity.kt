package com.example.sqlite_practice

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sqlite_practice.databinding.ActivityAuthBinding
import com.example.sqlite_practice.db.DbHelper


class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.auth)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnToRegFromAuth.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnAuth.setOnClickListener {
            val userName = binding.editNameAuth.text.toString().trim()
            val userSurName = binding.editSurNameAuth.text.toString().trim()

            if (userName == "" || userSurName == "") {
                Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_SHORT).show()
            } else {

                val db = DbHelper(this, null)
                val isAuth = db.getUser(userName, userSurName)

                if (isAuth) {
                    Toast.makeText(
                        this,
                        "Пользователь с именем $userName авторизован",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.editNameAuth.text.clear()
                    binding.editSurNameAuth.text.clear()
                    val intent = Intent(this, BaseActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Пользователь с именем $userName не авторизован", Toast.LENGTH_SHORT).show()


                }

            }

        }

    }
}