package com.codecx.project_g11

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.codecx.project_g11.databinding.ListItemLessonsBinding

class LessonListAdapter (context: Context, var datasource: ArrayList<Lesson>):
    ArrayAdapter<Lesson>(context , 0, datasource){
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val currentLesson = getItem(position)

        val itemLessonBinding = ListItemLessonsBinding.inflate(LayoutInflater.from(context), parent, false)

        val itemView = itemLessonBinding.root

        if (currentLesson != null) {
            itemLessonBinding.tvLessonName.text = currentLesson.title
            itemLessonBinding.tvLessonLength.text = "Length: ${currentLesson.duration}"
            itemLessonBinding.imgLesson.setImageResource(currentLesson.id_image)

            if(currentLesson.isCompleted){
                itemLessonBinding.imgLessonComplt.visibility = View.VISIBLE
            }
        }

        return itemView
    }
}