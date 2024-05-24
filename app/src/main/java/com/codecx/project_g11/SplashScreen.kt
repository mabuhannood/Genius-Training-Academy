package com.codecx.project_g11

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.WindowManager
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.example.project_g08.Datasource
import com.google.android.material.button.MaterialButton


class SplashScreen : AppCompatActivity() {

    lateinit var sharedPrefs: SharedPreferences
    lateinit var dataSource: Datasource

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

            dataSource = Datasource.getInstance()
            sharedPrefs = this.getSharedPreferences("KEY_PREFS_FILES", MODE_PRIVATE)

            if (checkIfNameExists()) {
                fillDataSource() // fill the data source
                val intent = Intent(this, WelcomeBackActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, EnterNameActivity::class.java)
                startActivity(intent)
            }

            finish()
        }

    }
    private fun checkIfNameExists(): Boolean {
        return if (sharedPrefs.contains("KEY_NAME")) {
            val username = sharedPrefs.getString("KEY_NAME", "")
            username?.isNotBlank() ?: false

        } else {
            false
        }

    }
    private fun getCompletedClasses(): MutableSet<String>? {
        return if (sharedPrefs.contains("PREFS_COMPLETED_CLASSES")) {
            sharedPrefs.getStringSet("PREFS_COMPLETED_CLASSES", null)
        } else {
            null
        }
    }

    private fun fillDataSource() {
        with(sharedPrefs.edit()) {

            val username = sharedPrefs.getString("KEY_NAME", "")
            dataSource.username = if(username?.isNotBlank() == true) username else ""

            val completedClasses: MutableSet<String>? = getCompletedClasses()

            if (completedClasses != null) {

                for (lesson in dataSource.lessonList) {
                    if (lesson.id.toString() in completedClasses) {
                        lesson.isCompleted = true
                    }

                }
            }

            for (lesson in dataSource.lessonList){
                val note:String? = sharedPrefs.getString("${"PREFS_NOTE"}${lesson.id}", null) // Note_id
                if(note != null){
                    lesson.notes = note
                }
            }
        }
    }

}