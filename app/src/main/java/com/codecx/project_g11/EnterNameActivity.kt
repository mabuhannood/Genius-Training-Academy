package com.codecx.project_g11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout

class EnterNameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_name)
        val btnContinue = findViewById<MaterialButton>(R.id.btnContinue)
        val txtName = findViewById<TextInputLayout>(R.id.txtName)
        btnContinue.setOnClickListener {
            val name = txtName.editText?.text?.toString()
            if (name?.isEmpty() == true) {
                txtName.error = "Required..."
            } else {
                startActivity(Intent(this, WelcomeBackActivity::class.java).also {
                    it.putExtra("name", name)
                })
            }

        }
        txtName.editText?.doOnTextChanged { text, start, before, count ->
            if (text?.isNotEmpty() == true) {
                if (txtName.error != null) {
                    txtName.error = null
                }
            }
        }
    }
}