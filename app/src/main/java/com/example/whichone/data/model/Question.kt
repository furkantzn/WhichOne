package com.example.whichone.data.model

data class Question(
    val id:String,
    val questionText:String,
    val options:List<Option>,
    var userAnswer:String
)
