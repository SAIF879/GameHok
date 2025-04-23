package com.example.gamehok.domain.usecases


import com.example.gamehok.data.model.TournamentData
import com.example.gamehok.data.util.ApiResult
import com.example.gamehok.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTournamentsUseCase @Inject constructor(private val repository: HomeRepository)  {
    suspend operator fun invoke() : Flow<ApiResult<List<TournamentData>>> = repository.getTournaments()
}