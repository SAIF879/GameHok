package com.example.gamehok.data.repo

import com.example.gamehok.data.model.GameData
import com.example.gamehok.data.model.TournamentData
import com.example.gamehok.data.source.remote.ApiService
import com.example.gamehok.data.util.ApiResult
import com.example.gamehok.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val apiService: ApiService) : HomeRepository {

    override suspend fun getGames(): Flow<ApiResult<List<GameData>>> = flow {
        emit(ApiResult.Loading)
        val response = apiService.getGames()
        if (response.isSuccessful && response.body() != null) {
            emit(ApiResult.Success(response.body()!!))
        } else {
            emit(ApiResult.Error("Error: ${response.message()}"))
        }
    }


    override suspend fun getTournaments(): Flow<ApiResult<List<TournamentData>>>  = flow{
        emit(ApiResult.Loading)
        val response = apiService.getTournaments()
        if (response.isSuccessful && response.body() != null) {
            emit(ApiResult.Success(response.body()!!))
        } else {
            emit(ApiResult.Error("Error: ${response.message()}"))
        }
    }
}
