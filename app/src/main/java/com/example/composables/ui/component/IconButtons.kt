package com.example.composables.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import com.example.composables.R
import com.example.composables.ui.common.HapticFeedback.slightHapticFeedback


@Composable
fun BackButton(onClick: () -> Unit) {
    val view = LocalView.current
    IconButton(modifier = androidx.compose.ui.Modifier, onClick = {
        onClick()
        view.slightHapticFeedback()
    }) {
        Icon(
            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
            contentDescription = stringResource(R.string.back),
        )
    }
}