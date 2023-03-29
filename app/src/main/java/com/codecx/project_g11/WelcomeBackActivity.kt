package com.codecx.project_g11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class WelcomeBackActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_back)
        val tvHeader = findViewById<TextView>(R.id.tvWelcome)
        tvHeader.append(intent?.extras?.getString("name", "Name") ?: "Name")
    }
}