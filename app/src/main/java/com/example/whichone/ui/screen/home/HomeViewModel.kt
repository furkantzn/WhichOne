package com.example.whichone.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whichone.data.model.Option
import com.example.whichone.data.model.Question
import com.example.whichone.data.repository.QuestionRepository
import com.example.whichone.utils.QuizStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val questionRepository: QuestionRepository
): ViewModel() {
    private val _quizStatus = MutableStateFlow(QuizStatus.ASKING_QUESTIONS)
    val quizStatus:StateFlow<QuizStatus> = _quizStatus.asStateFlow()
    private val _questions = MutableStateFlow<List<Question>>(emptyList())
    val questions = _questions.asStateFlow()

    private val _currentQuestion = MutableStateFlow<Question?>(null)
    val currentQuestion: StateFlow<Question?> = _currentQuestion.asStateFlow()

    private val _userScore = MutableStateFlow(0)
    val userScore : StateFlow<Int> = _userScore.asStateFlow()
    private var currentIndex = 0

    fun initQuestions() {
        currentIndex = 0
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _questions.value = questionRepository.fetchQuestions().firstOrNull()!!
                getNextQuestion()
            }
        }
    }

    fun getNextQuestion() {
        currentIndex += 1
        if(!checkNextIndexIsExist()){
            _quizStatus.value = QuizStatus.RESULT
            _currentQuestion.value = null
            return
        }
        viewModelScope.launch {
            withContext(Dispatchers.Main.immediate){
                _currentQuestion.value = _questions.value[currentIndex]
            }
        }
    }

    fun addUserAnswer(userAnswer: String){
        _currentQuestion.value?.userAnswer = userAnswer
    }

    private fun checkNextIndexIsExist():Boolean{
        return currentIndex <= _questions.value.size-1
    }

    fun addScore(score:Int){
        _userScore.value += score
    }

    fun findCorrectOption(question: Question?):Option?{
        for (option in question?.options ?: emptyList()) {
            if(option.isCorrect){
                return option
            }
        }
        return null
    }
    fun getAnswerStatus(question: Question?):Boolean{
        for (option in question?.options ?: emptyList()) {
            if(option.name == (question?.userAnswer ?: "")){
                return true
            }
        }
        return false
    }
}