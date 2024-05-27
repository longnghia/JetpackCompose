package com.example.composables.ui.page.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AboutPage(onNavigateBack: () -> Unit, onNavigateToDonatePage: () -> Unit) {
    Column {
        Text(text = "This is about page")
        Button(onClick = {
            onNavigateToDonatePage()
        }) {
            Text(text = "Donate")
        }
        Button(onClick = {
            onNavigateBack()
        }) {
            Text(text = "Back")
        }
    }
}
