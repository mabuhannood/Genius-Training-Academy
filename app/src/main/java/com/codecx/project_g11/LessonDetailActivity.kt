package com.codecx.project_g11

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.codecx.project_g11.databinding.ActivityLessonDetailBinding
import com.example.project_g08.Datasource

class LessonDetailActivity : AppCompatActivity() {
    lateinit var datasource: Datasource
    lateinit var binding: ActivityLessonDetailBinding
    lateinit var sharedPrefs: SharedPreferences
    lateinit var lessonsList: ArrayList<Lesson>
    lateinit var lessonsAdapter: LessonListAdapter
    lateinit var selectedLesson: Lesson
    var t: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    @SuppressLint("SetTextI18n", "SetJavaScriptEnabled")
    override fun onStart() {
        super.onStart()
        datasource = Datasource.getInstance()
        sharedPrefs = this.getSharedPreferences("KEY_PREFS_FILES", MODE_PRIVATE)

        val selectedLessonPosition = datasource.selectedLessonPosition
        if (selectedLessonPosition != null) {
            selectedLesson = datasource.lessonList[selectedLessonPosition]

            lessonsList = datasource.lessonList
            binding.tvLessonTitle.text = "${selectedLesson.id}. ${selectedLesson.title}"
            binding.tvLessonLength.text = "Length: ${selectedLesson.duration}"
            binding.tvLessonInfo.text = selectedLesson.lessonInfo

            if (selectedLesson.notes != "") {
                binding.etNotes.setText(selectedLesson.notes)
            }

            binding.btnWatchLesson.setOnClickListener {
                println("selected lesson : ${selectedLesson.videoURL}")
                val webview = binding.wvVideoPlayer
                webview.settings.javaScriptEnabled = true
                webview.loadUrl(selectedLesson.videoURL)
            }

            binding.btnSaveNote.setOnClickListener {
                val notesFromUser = binding.etNotes.text.toString()

                if(notesFromUser.isNotBlank()){
                    if (binding.tvNotesError.isVisible) {
                        binding.tvNotesError.visibility = View.GONE
                        binding.tvNotesError.text = ""
                    }
                    selectedLesson.notes = notesFromUser
                    addNotesToPrefs()
                    t?.cancel()
                    t = Toast.makeText(
                        this@LessonDetailActivity,
                        "Notes Saved",
                        Toast.LENGTH_SHORT
                    )
                    t?.show()
                }
                else{
                    binding.tvNotesError.isVisible = true
                    binding.tvNotesError.text = "Note is empty"
                }
            }


            binding.btnMarkComplete.setOnClickListener {
                selectedLesson.isCompleted = true

                addCompletedClass()
                finish()
            }
        }

    }

    @SuppressLint("MutatingSharedPrefs")
    private fun addCompletedClass(){

        var completedClasses:MutableSet<String>? = sharedPrefs.getStringSet("PREFS_COMPLETED_CLASSES", null)


        if (completedClasses != null) {
            completedClasses.add("${selectedLesson.id}")
        }
        else{
            completedClasses = mutableSetOf("${selectedLesson.id}")
        }
        with(sharedPrefs.edit()) {
            // write to sharedPreferences
            remove("PREFS_COMPLETED_CLASSES")
            apply()
            putStringSet("PREFS_COMPLETED_CLASSES", completedClasses) // key value pair
            apply()
        }
    }
    private fun addNotesToPrefs(){

        val prefStringName = "${"PREFS_NOTE"}${selectedLesson.id}" //Note_id

        with(sharedPrefs.edit()) {
            // write to sharedPreferences
            putString(prefStringName, binding.etNotes.text.toString()) // key value pair
            apply()
        }
    }

    override fun onPause() {
        super.onPause()
        t?.cancel() // cancel any toasts if exists
    }
}
