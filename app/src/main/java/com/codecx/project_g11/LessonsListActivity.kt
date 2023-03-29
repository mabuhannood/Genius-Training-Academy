package com.codecx.project_g11

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.codecx.project_g11.databinding.ActivityLessonsListBinding
import com.example.project_g08.Datasource
import com.example.project_g08.Lesson

class LessonsListActivity : AppCompatActivity() {
    lateinit var dataSource: Datasource
    lateinit var binding: ActivityLessonsListBinding
    lateinit var lessonsList: ArrayList<Lesson>
    lateinit var lessonsAdapter: LessonListAdapter
    var isSequential: Boolean = false
    var t: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    override fun onStart() {
        super.onStart()
        dataSource  = Datasource.getInstance()

        lessonsList = dataSource.lessonList

        lessonsAdapter = LessonListAdapter(this, lessonsList)
        binding.lvLessons.adapter = lessonsAdapter

        binding.swSequential.setOnClickListener {
            isSequential = binding.swSequential.isChecked

        }

        binding.lvLessons.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectedLesson = lessonsList[position]

            dataSource.selectedLessonPosition = position

            if (!isSequential || position == 0) {
                val intent = Intent(this, LessonDetailActivity::class.java)
                startActivity(intent)

            } else {
                if (lessonsList[position - 1].isCompleted) {
                    val intent = Intent(this, LessonDetailActivity::class.java)
                    startActivity(intent)
                } else {
                    t?.cancel()
                    t = Toast.makeText(
                        this@LessonsListActivity,
                        "Not allowed while in sequential mode",
                        Toast.LENGTH_SHORT
                    )
                    t?.show()
                }
            }


        }

    }
}