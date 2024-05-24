package com.codecx.project_g11

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.codecx.project_g11.databinding.ActivityLessonsListBinding
import com.codecx.project_g11.databinding.ActivityWelcomeBackBinding
import com.example.project_g08.Datasource
import com.google.android.material.button.MaterialButton

class WelcomeBackActivity : AppCompatActivity() {
    lateinit var sharedPrefs: SharedPreferences

    lateinit var datasource: Datasource

    lateinit var binding: ActivityWelcomeBackBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBackBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun onStart() {
        super.onStart()

        datasource = Datasource.getInstance()
        sharedPrefs = this.getSharedPreferences("KEY_PREFS_FILES", MODE_PRIVATE)
        val username = sharedPrefs.getString("KEY_NAME", "")
        val tvHeader = binding.tvWelcome
        tvHeader.append(username ?: "Name")

        // get completed classes


        //Get completed classes from dataSource
        var numberOfCompletedClasses: Int = 0

        for (lesson in datasource.lessonList) {
            if (lesson.isCompleted) {
                numberOfCompletedClasses++
            }
        }

        // Total no of classes in data source
        val totalClass = datasource.lessonList.size

        //Calculate percentage of completed classes
        val completedPercentage = (100 / totalClass) * (numberOfCompletedClasses)
        binding.tvCompletionPercentage.text = "You've completed $completedPercentage% of the course!"
        //Calculate remaining classes
        var remainingClasses = totalClass - numberOfCompletedClasses
        //Set completed classes
        binding.tvLessonsCom.text = "Lessons completed: $numberOfCompletedClasses"

        binding.tvLessonsRem.text = "Lessons remaining: $remainingClasses"


        binding.btnContinue.setOnClickListener {
            val intent = Intent(this, LessonsListActivity::class.java)
            startActivity(intent)
        }

        binding.btnDelete.setOnClickListener {
            with(sharedPrefs.edit()) {
                clear() // to delete all the keys
                apply()
            }
            datasource.reset()
            val intent = Intent(this, EnterNameActivity::class.java)
            startActivity(intent)
        }
    }
}