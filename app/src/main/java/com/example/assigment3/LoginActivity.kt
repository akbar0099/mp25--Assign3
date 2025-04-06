package com.example.assigment3

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailField = findViewById<EditText>(R.id.editTextEmail)
        val passwordField = findViewById<EditText>(R.id.editTextPassword)
        val loginButton = findViewById<Button>(R.id.buttonLogin)
        val goToRegisterText = findViewById<TextView>(R.id.textViewGoToRegister) // Perbaikan ID

        val sharedPreferences: SharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        loginButton.setOnClickListener {
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()

            // Ambil data dari SharedPreferences
            val registeredEmail = sharedPreferences.getString("email", null)
            val registeredPassword = sharedPreferences.getString("password", null)
            val registeredName = sharedPreferences.getString("name", "User")

            // Cek apakah ada akun yang sudah terdaftar
            if (registeredEmail == null || registeredPassword == null) {
                Toast.makeText(this, "Belum ada akun terdaftar. Silakan daftar dulu!", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, RegisterActivity::class.java)) // Arahkan ke RegisterActivity
                return@setOnClickListener
            }

            if (email.isNotEmpty() && password.isNotEmpty()) {
                if (email == registeredEmail && password == registeredPassword) {
                    // Login berhasil, kirim data ke LandingActivity
                    val user = User(name = registeredName ?: "User", email = email)
                    val intent = Intent(this, LandingActivity::class.java)
                    intent.putExtra("user", user)
                    intent.putExtra("isRegistering", false) // ⬅️ Kirim flag bahwa ini dari login
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Email atau password salah!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Email dan password harus diisi", Toast.LENGTH_SHORT).show()
            }
        }

        // Event listener untuk TextView "Belum punya akun?"
        goToRegisterText.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}
