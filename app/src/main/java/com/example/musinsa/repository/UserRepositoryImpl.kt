package com.example.musinsa.repository

import com.example.musinsa.model.User
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl @Inject constructor(
    private val localDataSource: UserLocalDataSource
): UserRepository {
    override suspend fun signUp(idText: String, pwText: String) {
        localDataSource.signUp(User(idText,pwText))
    }

    override suspend fun getUser(): Flow<User> = localDataSource.getUser()
}