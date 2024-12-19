package com.example.whichone.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.baseproject.R

@Composable
fun QuizResultCard(
    question: String,
    userAnswer: String,
    correctAnswer: String,
    answerStatus: Boolean,
    modifier: Modifier = Modifier
) {
    val iconId = if (answerStatus) {
        R.drawable.ic_correct
    } else {
        R.drawable.ic_wrong
    }
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(0.7f)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = question,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.background
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Your answer: $userAnswer",
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Correct answer: $correctAnswer",
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Image(
                modifier = Modifier.weight(0.3f),
                painter = painterResource(id = iconId),
                contentDescription = "Custom Icon",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.background)
            )
        }
    }
}