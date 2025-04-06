package com.example.assigment3

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LandingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        val welcomeMessage = findViewById<TextView>(R.id.textViewWelcome)
        val backToLoginButton = findViewById<Button>(R.id.buttonBackToLogin)

        // Cek apakah pengguna datang dari RegisterActivity
        val isRegistering = intent.getBooleanExtra("isRegistering", false)

        // Tampilkan tombol hanya jika dari registrasi
        if (isRegistering) {
            backToLoginButton.visibility = View.VISIBLE
            backToLoginButton.setOnClickListener {
                finish() // Kembali ke halaman login
            }
        } else {
            backToLoginButton.visibility = View.GONE
        }

        // Ambil objek User dari intent
        val user: User? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("user", User::class.java)
        } else {
            intent.getParcelableExtra("user")
        }

        // Atur teks berdasarkan asal pengguna
        if (user != null) {
            welcomeMessage.text = if (isRegistering) {
                "Registrasi berhasil! Selamat datang, ${user.name}!"
            } else {
                "Selamat datang, ${user.name}!"
            }
        } else {
            welcomeMessage.text = "Selamat datang!"
        }
    }

}
