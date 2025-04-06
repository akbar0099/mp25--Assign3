package com.example.assigment3

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        val nameField = findViewById<EditText>(R.id.input_name)
        val emailField = findViewById<EditText>(R.id.input_email)
        val passwordField = findViewById<EditText>(R.id.input_password)
        val registerButton = findViewById<Button>(R.id.btn_register)

        val sharedPreferences: SharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        registerButton.setOnClickListener {
            val name = nameField.text.toString().trim()
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()

            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                // Simpan data ke SharedPreferences
                val editor = sharedPreferences.edit()
                editor.putString("name", name)
                editor.putString("email", email)
                editor.putString("password", password)
                editor.apply()

                Toast.makeText(this, "Registrasi berhasil!", Toast.LENGTH_SHORT).show()

                // Buat objek User dan kirim ke LandingActivity
                val user = User(name, email)
                val intent = Intent(this, LandingActivity::class.java)
                intent.putExtra("user", user) // Kirim user sebagai Parcelable
                intent.putExtra("isRegistering", true) // ⬅️ Kirim flag bahwa ini dari registrasi
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
