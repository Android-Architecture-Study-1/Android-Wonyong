package com.example.musinsa.usecase

import com.example.musinsa.model.User
import com.example.musinsa.repository.UserRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Flow<User> = userRepository.getUser()
}