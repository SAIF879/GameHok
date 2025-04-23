package com.example.gamehok.data.source.remote

import com.example.gamehok.data.model.GameData
import com.example.gamehok.data.model.TournamentData
import retrofit2.Response
import retrofit2.http.GET


interface ApiService{

    @GET("/games")
    suspend fun getGames(): Response<List<GameData>>

    @GET("/tournaments")
    suspend fun getTournaments(): Response<List<TournamentData>>
}