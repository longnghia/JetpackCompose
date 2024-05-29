package com.example.composables.ui.page.api

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composables.ApiUtil
import com.example.composables.ui.component.BackButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun ApiPage(onNavigateBack: () -> Unit) {
    val state by ApiUtil.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        Log.d("ApiPage", "ApiPage: LaunchedEffect")
        launch(Dispatchers.IO) {
            runCatching {
                ApiUtil.fetchData()
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    Scaffold(topBar = {
        BackButton {
            onNavigateBack()
        }
    }) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Column {

                Text(text = "Api Page")
                if (state is ApiUtil.State.Loading) {
                    Text(text = "Loading", color = Color.Black)
                }
                if (state is ApiUtil.State.Success) {
                    (state as ApiUtil.State.Success).data.map {
                        Text(
                            text = it.tagName ?: "",
                            color = Color.Green
                        )
                    }
                }
                if (state is ApiUtil.State.Failure) {
                    Text(
                        text = "${(state as ApiUtil.State.Failure).error.message}",
                        color = Color.Red
                    )
                }
            }
        }
    }
}
