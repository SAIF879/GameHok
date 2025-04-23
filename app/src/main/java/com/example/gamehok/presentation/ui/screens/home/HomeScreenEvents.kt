package com.example.gamehok.presentation.ui.screens.home

sealed class HomeScreenEvents {
    data class OnNavigate(val link: String?) : HomeScreenEvents()
}