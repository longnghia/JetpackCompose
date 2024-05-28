package com.example.composables.ui.common

import androidx.compose.runtime.compositionLocalOf

const val DEFAULT_SEED_COLOR = 0xa3d48d


val LocalDynamicColorSwitch = compositionLocalOf { false }
val LocalPaletteStyleIndex = compositionLocalOf { 0 }
val LocalSeedColor = compositionLocalOf { DEFAULT_SEED_COLOR }