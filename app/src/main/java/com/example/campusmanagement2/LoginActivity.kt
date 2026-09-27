package com.example.campusmanagement2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check if user is already logged in
        val sharedPref = getSharedPreferences("UserSession", Context.MODE_PRIVATE)
        if (sharedPref.getBoolean("isLoggedIn", false)) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        setContentView(R.layout.activity_login)

        val etStudentId = findViewById<EditText>(R.id.etStudentId)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val studentId = etStudentId.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (studentId.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both Student ID and Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Simple demo validation (replace with API/Database check as needed)
            if (password == "123456") {
                // Save session in SharedPreferences
                val editor = sharedPref.edit()
                editor.putBoolean("isLoggedIn", true)
                editor.putString("studentId", studentId)
                editor.apply()

                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()

                // Redirect to Dashboard (MainActivity)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Invalid credentials! Use password: 123456", Toast.LENGTH_SHORT).show()
            }
        }
    }
}