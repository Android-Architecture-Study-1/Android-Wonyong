package com.example.musinsa.repository

import com.example.musinsa.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun signUp(idText:String,pwText:String)
    suspend fun getUser(): Flow<User>
}