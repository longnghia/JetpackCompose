package com.example.composables

import android.app.Application
import androidx.compose.ui.platform.ClipboardManager
import com.tencent.mmkv.MMKV
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        MMKV.initialize(this)
        applicationScope = CoroutineScope(SupervisorJob())
    }

    companion object {
        lateinit var clipboard: ClipboardManager
        lateinit var videoDownloadDir: String
        lateinit var audioDownloadDir: String
        lateinit var applicationScope: CoroutineScope
    }
}