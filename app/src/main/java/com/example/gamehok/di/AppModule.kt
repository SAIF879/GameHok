package com.example.gamehok.di


import com.example.gamehok.data.repo.HomeRepositoryImpl
import com.example.gamehok.data.source.remote.ApiService
import com.example.gamehok.data.util.OkHttpClientHelper
import com.example.gamehok.domain.repository.HomeRepository
import com.example.gamehok.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGameHokApi(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClientHelper().getOkHttpClient())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }


    @Singleton
    @Provides
    fun providesGetTournamentRepository(apiServices: ApiService): HomeRepository {
        return HomeRepositoryImpl(apiServices)
    }

}
