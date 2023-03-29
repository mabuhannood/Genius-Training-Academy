package com.codecx.project_g11

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.WindowManager
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        setContentView(R.layout.activity_splash_screen)

        val video = Uri.parse(
            "android.resource://" + packageName + "/"
                    + R.raw.splashvideo
        )
        val videoView = findViewById<VideoView>(R.id.mVideoViewq)
        videoView.setVideoURI(video)
        videoView.start()
        findViewById<MaterialButton>(R.id.btnLearn).setOnClickListener {
            startActivity(Intent(this, EnterNameActivity::class.java))
            finish()
        }

    }
}