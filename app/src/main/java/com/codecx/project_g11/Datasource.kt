package com.example.project_g08

import com.codecx.project_g11.Lesson

class Datasource {


    private constructor() {
        lessonList = arrayListOf()
        lessonList.add(Lesson(1, "Introduction to the Course","35 min", "In summary, this course will provide you with a comprehensive introduction to the essential building blocks of web development: HTML for content and structure, CSS for presentation and styling, and JavaScript for interactivity and dynamic content.", false, "https://www.youtube.com/watch?v=_GTMOmRrqkU", ""))
        lessonList.add(Lesson(2, "HTML","1 hr 10 min", "HyperText Markup Language, commonly abbreviated as HTML, is the standard markup language used to create web pages. Along with CSS, and JavaScript, HTML is a cornerstone technology used to create web pages as well as to create user interfaces for mobile and web applications.", false, "https://www.youtube.com/watch?v=qz0aGYrrlhU", ""))
        lessonList.add(Lesson(3, "CSS","1 hr 25 min", "This course introduces students to the fundamentals of CSS (Cascading Style Sheets), used to add style and layout to web pages. In this course, students will be introduced to the CSS language, and how it can be leveraged to style a responsive web page and format content into a desirable layout.", false, "https://www.youtube.com/watch?v=ieTHC78giGQ", ""))
        lessonList.add(Lesson(4, "What is Javascript?","3 hr 27 min", "Through taking this course, students should: Develop familiarity with the JavaScript language. Learn to use best-practice idioms and patterns. Understand concepts commonly used in dynamic language programming, such as introspection, higher-order functions, and closures.", false, "https://www.youtube.com/watch?v=PkZNo7MFNFg&t=1s", ""))
        lessonList.add(Lesson(5, "Variables and Conditionals","20 min", "Conditional statements control behavior in JavaScript and determine whether or not pieces of code can run.", false, "https://www.youtube.com/watch?v=g5203LerCmw", ""))
        lessonList.add(Lesson(6, "Loops","11 min", "Loops are used in JavaScript to perform repeated tasks based on a condition. Conditions typically return true or false . A loop will continue running until the defined condition returns false.", false, "https://www.youtube.com/watch?v=LuAWFium1nk", ""))


    }
    companion object {
        @Volatile
        private lateinit var instance: Datasource
        fun getInstance(): Datasource {
            synchronized(this) {
                if (!::instance.isInitialized) {
                    instance = Datasource()
                }
                return instance
            }
        }
    }

    var lessonList:ArrayList<Lesson> = arrayListOf()

    var selectedLessonPosition:Int? = null

    var username:String = ""

    fun reset() {
        for(lesson in lessonList){
            lesson.isCompleted = false
            lesson.notes = ""
        }
    }
}