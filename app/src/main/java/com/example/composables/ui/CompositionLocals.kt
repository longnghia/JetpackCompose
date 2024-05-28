package com.example.composables.ui

import android.os.Build
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composables.ui.common.DEFAULT_SEED_COLOR
import com.example.composables.util.PreferenceUtil
import com.example.composables.util.paletteStyles
import com.kyant.monet.LocalTonalPalettes
import com.kyant.monet.PaletteStyle
import com.kyant.monet.TonalPalettes.Companion.toTonalPalettes

val LocalSeedColor = compositionLocalOf { DEFAULT_SEED_COLOR }
val LocalWindowWidthState = staticCompositionLocalOf { WindowWidthSizeClass.Compact }
val LocalDynamicColorSwitch = compositionLocalOf { false }
val LocalPaletteStyleIndex = compositionLocalOf { 0 }

@Composable
fun SettingsProvider(windowWidthSizeClass: WindowWidthSizeClass, content: @Composable () -> Unit) {
    PreferenceUtil.AppSettingsStateFlow.collectAsStateWithLifecycle().value.run {
        CompositionLocalProvider(
            LocalSeedColor provides seedColor,
            LocalPaletteStyleIndex provides paletteStyleIndex,
            LocalTonalPalettes provides if (isDynamicColorEnabled && Build.VERSION.SDK_INT >= 31) dynamicDarkColorScheme(
                LocalContext.current
            ).toTonalPalettes()
            else Color(seedColor).toTonalPalettes(
                paletteStyles.getOrElse(paletteStyleIndex) { PaletteStyle.TonalSpot }
            ),
            LocalWindowWidthState provides windowWidthSizeClass,
            LocalDynamicColorSwitch provides isDynamicColorEnabled,
            content = content
        )
    }
}