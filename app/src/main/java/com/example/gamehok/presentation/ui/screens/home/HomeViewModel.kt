package com.example.gamehok.presentation.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamehok.data.model.GameData
import com.example.gamehok.data.model.TournamentData
import com.example.gamehok.data.util.ApiResult
import com.example.gamehok.domain.usecases.GetGamesUseCase
import com.example.gamehok.domain.usecases.GetTournamentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTournamentsUseCase: GetTournamentsUseCase,
    private val getGamesUseCase: GetGamesUseCase
) : ViewModel() {

    init {
        fetchGames()
    }

    private val _tournamentList =
        MutableStateFlow<ApiResult<List<TournamentData>>>(ApiResult.Loading)
    val tournamentList: StateFlow<ApiResult<List<TournamentData>>> = _tournamentList.asStateFlow()
    private val _gameList = MutableStateFlow<ApiResult<List<GameData>>>(ApiResult.Loading)
    val gameList: StateFlow<ApiResult<List<GameData>>> = _gameList.asStateFlow()


    private fun fetchTournaments() {
        viewModelScope.launch(Dispatchers.IO) {
            getTournamentsUseCase().collect { result ->
                _tournamentList.update {
                    result
                }
            }
        }
    }

    private fun fetchGames() {
        viewModelScope.launch(Dispatchers.IO) {
            getGamesUseCase().collect { result ->
                _gameList.update {
                    result
                }
            }
        }
    }


}
