package com.example.composables.ui.page.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomePage(navigateToSettings: () -> Unit, navigateToApi: () -> Unit) {
    Column {
        Text(text = "This is home page")
        Button(onClick = {
            navigateToSettings()
        }) {
            Text(text = "Setting")
        }
        Button(onClick = {
            navigateToApi()
        }) {
            Text(text = "Api Call")
        }
    }
}