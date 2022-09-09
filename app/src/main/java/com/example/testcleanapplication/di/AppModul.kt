package com.example.testcleanapplication.di

import com.example.testcleanapplication.data.remote.ApiService
import com.example.testcleanapplication.data.remote.RetrofitClient
import com.example.testcleanapplication.data.repository.RepositoryImpl
import com.example.testcleanapplication.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return RetrofitClient().buildService(ApiService::class.java)
    }


    @Provides
    @Singleton
    fun provideCoinRepository(api: ApiService): UserRepository {
        return RepositoryImpl(api)
    }

}
