package com.example.gamehok.domain.repository

import com.example.gamehok.data.model.GameData
import com.example.gamehok.data.model.TournamentData
import com.example.gamehok.data.util.ApiResult
import kotlinx.coroutines.flow.Flow


interface HomeRepository{
    suspend fun getGames() : Flow<ApiResult<List<GameData>>>
    suspend fun getTournaments() : Flow<ApiResult<List<TournamentData>>>
}