package com.example.whichone.ui.screen.home

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.whichone.utils.QuizStatus
import com.example.whichone.ui.component.FlipCard
import com.example.whichone.ui.component.QuizResultCard
import com.example.whichone.ui.theme.WhichOneTheme
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val quizStatus by viewModel.quizStatus.collectAsState()
    val currentQuestion by viewModel.currentQuestion.collectAsState()
    val score by viewModel.userScore.collectAsState()
    val questions by viewModel.questions.collectAsState()
    val disabledCards = remember {
        mutableStateOf(false)
    }
    val scale = remember { Animatable(0f) }
    val progress = remember { Animatable(1f) }
    LaunchedEffect(Unit) {
        viewModel.initQuestions()
    }
    LaunchedEffect(progress.value) {
        if (progress.value == 0f) {
            disabledCards.value = false
            viewModel.getNextQuestion()
        }
    }
    LaunchedEffect(currentQuestion) {
        scale.snapTo(0f)
        scale.animateTo(
            targetValue = 1f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessVeryLow
            )
        )
        progress.snapTo(1f)
        progress.animateTo(
            targetValue = 0f,
            animationSpec = tween(
                durationMillis = 5000,
                easing = LinearEasing
            )
        )
    }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        when (quizStatus) {
            QuizStatus.ASKING_QUESTIONS -> {
                val (progressContent, question, scoreRef, cards) = createRefs()
                LinearProgressIndicator(
                    progress = { progress.value },
                    modifier = Modifier
                        .constrainAs(progressContent) {
                            top.linkTo(parent.top, margin = 16.dp)
                            start.linkTo(parent.start, margin = 8.dp)
                            end.linkTo(parent.end, margin = 8.dp)
                            width = Dimension.fillToConstraints
                        }
                        .fillMaxWidth()
                        .height(8.dp),
                )
                Text(
                    modifier = Modifier
                        .constrainAs(question) {
                            top.linkTo(progressContent.bottom, margin = 16.dp)
                            start.linkTo(parent.start, margin = 8.dp)
                            end.linkTo(parent.end, margin = 8.dp)
                            width = Dimension.fillToConstraints
                        }
                        .graphicsLayer(
                            scaleX = scale.value,
                            scaleY = scale.value
                        ),
                    color = MaterialTheme.colorScheme.secondary,
                    text = currentQuestion?.questionText ?: "",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall
                )
                Row(
                    modifier = Modifier
                        .constrainAs(cards) {
                            top.linkTo(question.bottom, margin = 32.dp)
                            start.linkTo(parent.start, margin = 8.dp)
                            end.linkTo(parent.end, margin = 8.dp)
                            bottom.linkTo(scoreRef.top, margin = 32.dp)
                            width = Dimension.fillToConstraints
                            height = Dimension.fillToConstraints
                        }
                        .width(IntrinsicSize.Max)
                        .height(IntrinsicSize.Max),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    for (option in currentQuestion?.options ?: emptyList()) {
                        Spacer(modifier = Modifier.width(8.dp))
                        FlipCard(
                            option = option,
                            disabled = disabledCards.value,
                            modifier = Modifier
                                .weight(1f)
                                .graphicsLayer(
                                    scaleX = scale.value,
                                    scaleY = scale.value
                                )
                        ) { optionText ->
                            viewModel.addUserAnswer(optionText.name)
                            if (optionText.isCorrect) {
                                viewModel.addScore(20)
                            }
                            disabledCards.value = true
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
                Text(
                    modifier = Modifier
                        .constrainAs(scoreRef) {
                            bottom.linkTo(parent.bottom, margin = 16.dp)
                            start.linkTo(parent.start, margin = 8.dp)
                            end.linkTo(parent.end, margin = 8.dp)
                            width = Dimension.fillToConstraints
                        },
                    color = MaterialTheme.colorScheme.secondary,
                    text = "Score: $score",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall
                )
            }

            QuizStatus.RESULT -> {
                val (resultText) = createRefs()
                Column(
                    modifier = Modifier
                        .constrainAs(resultText) {
                            top.linkTo(parent.top, margin = 32.dp)
                            start.linkTo(parent.start, margin = 8.dp)
                            end.linkTo(parent.end, margin = 8.dp)
                            bottom.linkTo(parent.bottom, margin = 16.dp)
                            width = Dimension.fillToConstraints
                        },
                ) {
                    Text(
                        color = MaterialTheme.colorScheme.secondary,
                        text = "You have completed the quiz your score is $score check your answers.",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ){
                        items(questions) { question ->
                            QuizResultCard(
                                question = question.questionText,
                                userAnswer = question.userAnswer,
                                correctAnswer = viewModel.findCorrectOption(question)?.name ?: "",
                                answerStatus = viewModel.getAnswerStatus(question)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    WhichOneTheme {
        HomeScreen()
    }
}