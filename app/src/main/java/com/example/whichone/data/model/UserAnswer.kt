package com.example.whichone.data.model

data class UserAnswer (
    val id:String,
    val questionText:String,
    val correctAnswer:String,
    val userAnswer:String,
    val answerStatus:Boolean
)