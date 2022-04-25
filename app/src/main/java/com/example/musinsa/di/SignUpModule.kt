package com.example.musinsa.di

import android.content.Context
import com.example.musinsa.repository.UserLocalDataSource
import com.example.musinsa.repository.UserLocalDataSourceImpl
import com.example.musinsa.repository.UserRepository
import com.example.musinsa.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SignUpModule {
    @Provides
    @Singleton
    fun provideLocalDataSource(@ApplicationContext context: Context): UserLocalDataSource {
        return UserLocalDataSourceImpl(context)
    }

    @Provides
    @Singleton
    fun provideSignUpRepository(localDataSource: UserLocalDataSource): UserRepository {
        return UserRepositoryImpl(localDataSource)
    }
}