package com.example.composables.ui.common

object Route {
    const val HOME = "home"

    const val SETTINGS = "settings"
    const val SETTINGS_PAGE = "settings_page"
    const val APPEARANCE = "appearance"
    const val DONATE = "donate"
    const val ABOUT = "about"
    const val LANGUAGES = "languages"

    const val API = "api"
}

infix fun String.arg(arg: String) = "$this/{$arg}"
infix fun String.id(id: Int) = "$this/$id"
