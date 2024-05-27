package com.example.composables.ui.page.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DonatePage(onNavigateBack: () -> Unit) {
    Column {
        Text(text = "This is donate page")
        Button(onClick = {
            onNavigateBack()
        }) {
            Text(text = "Back")
        }
    }
}
