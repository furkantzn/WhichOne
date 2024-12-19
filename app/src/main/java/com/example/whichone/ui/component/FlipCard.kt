package com.example.whichone.ui.component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.whichone.data.model.Option
import com.example.whichone.utils.CardStatus

@Composable
fun FlipCard(
    option: Option,
    disabled:Boolean,
    modifier: Modifier = Modifier,
    checkAnswer: (option:Option) -> Unit,
) {
    var status by remember {
        mutableStateOf(CardStatus.DEFAULT)
    }
    var isFlipped by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
    )
    val cardText: String
    val cardColor: Color
    val textColor : Color
    val borderWidth = if (status != CardStatus.DEFAULT){
        4.dp
    }else{
        2.dp
    }
    val borderColor: Color = when (status) {
        CardStatus.CORRECT -> Color(0xFF2ECC71)
        CardStatus.DEFAULT -> MaterialTheme.colorScheme.secondary
        CardStatus.WRONG -> Color(0xFFE74C3C)
    }

    if (rotation <= 90f) {
        cardText = option.name
        textColor = MaterialTheme.colorScheme.secondary
        cardColor = MaterialTheme.colorScheme.primary
    } else {
        cardText = option.description
        cardColor = MaterialTheme.colorScheme.secondary
        textColor = MaterialTheme.colorScheme.primary
    }

    LaunchedEffect(option) {
        status = CardStatus.DEFAULT
        isFlipped = false
    }

    Card(
        colors = CardDefaults.cardColors(containerColor = cardColor),
        modifier = modifier
            .fillMaxWidth()
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 12f * density
            }
            .border(
                width = borderWidth,
                color = borderColor,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable {
                if (!disabled){
                    checkAnswer(option)
                    status = if (option.isCorrect) {
                        CardStatus.CORRECT
                    } else {
                        CardStatus.WRONG
                    }
                    isFlipped = !isFlipped
                }
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = cardText,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyMedium,
                color = textColor,
                modifier = Modifier.graphicsLayer { rotationY = rotation }
            )
        }
    }
}