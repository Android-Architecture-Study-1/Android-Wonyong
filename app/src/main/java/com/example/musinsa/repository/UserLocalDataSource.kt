package com.example.musinsa.repository

import com.example.musinsa.model.User
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {
    suspend fun signUp(user: User)
    suspend fun getUser(): Flow<User>
}