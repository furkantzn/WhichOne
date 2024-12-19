package com.example.whichone.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryButton(
    name: String,
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    contentDescription: String? = null,
    onPrimaryButtonClick: () -> Unit
) {
    ElevatedButton(
        modifier = modifier,
        onClick = onPrimaryButtonClick,
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 8.dp,
            pressedElevation = 16.dp
        ),
        contentPadding = PaddingValues(
            top = 8.dp,
            bottom = 8.dp,
            start = 16.dp, // Adjusted for icon
            end = 16.dp
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp) // Space between icon and text
        ) {
            icon?.let {
                Icon(
                    painter = it,
                    contentDescription = contentDescription,
                    tint = MaterialTheme.colorScheme.onPrimary, // Adjust icon color
                    modifier = Modifier.size(20.dp) // Adjust icon size if needed
                )
            }
            Text(
                text = name,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun SecondaryButton(
    name: String,
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    contentDescription: String? = null,
    onSecondaryButtonClick:()->Unit
) {
    ElevatedButton(
        modifier = modifier,
        onClick = onSecondaryButtonClick,
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = MaterialTheme.colorScheme.background,
            disabledContainerColor = MaterialTheme.colorScheme.secondary
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 8.dp,
            pressedElevation = 16.dp
        ),
        contentPadding = PaddingValues(
            top = 8.dp,
            bottom = 8.dp,
            start = 32.dp,
            end = 32.dp
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp) // Space between icon and text
        ) {
            icon?.let {
                Icon(
                    painter = it,
                    contentDescription = contentDescription,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
            }
            Text(
                text = name,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }

}