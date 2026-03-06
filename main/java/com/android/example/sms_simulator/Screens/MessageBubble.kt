package com.android.example.sms_simulator.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp

@Composable
fun MessageBubble(message: AnnotatedString, from: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = from, style = MaterialTheme.typography.labelSmall)
        Surface(
            color = MaterialTheme.colorScheme.secondary,
            shape = MaterialTheme.shapes.medium,
            shadowElevation = 2.dp
        ) {
            Text(
                text = message,
                modifier = Modifier.padding(12.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
