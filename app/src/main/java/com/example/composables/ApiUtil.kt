package com.example.composables

import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request

object ApiUtil {
    private const val TAG = "ApiUtil"
    private val requestForRelease =
        Request.Builder().url("https://api.github.com/repos/JunkFood02/Seal/releases").build()
    private val client = OkHttpClient()
    private val jsonFormat = Json { ignoreUnknownKeys = true }

    private val _state = MutableStateFlow<State>(State.Idle)
    val state = _state.asStateFlow()

    sealed class State {
        data object Idle : State()
        data object Loading : State()
        data class Failure(
            val error: Exception = Exception()
        ) : State()

        data class Success(
            val data: List<LatestRelease> = emptyList()
        ) : State()
    }

    @Serializable
    data class LatestRelease(
        @SerialName("html_url") val htmlUrl: String? = null,
        @SerialName("tag_name") val tagName: String? = null,
        val name: String? = null,
        val draft: Boolean? = null,
        @SerialName("prerelease") val preRelease: Boolean? = null,
        @SerialName("created_at") val createdAt: String? = null,
        @SerialName("published_at") val publishedAt: String? = null,
//        val assets: List<AssetsItem>? = null,
        val body: String? = null,
    )

    fun fetchData() {
        _state.value = State.Loading
        try {
            client.newCall(requestForRelease).execute().run {
                val releaseList =
                    jsonFormat.decodeFromString<List<LatestRelease>>(this.body.string())
                Log.d(TAG, "fetchData: ${releaseList.map { it.tagName }}")
                body.close()
                _state.value = State.Success(releaseList)
            }
        } catch (error: java.io.IOException) {
            _state.value = State.Failure(error)
        }
    }
}