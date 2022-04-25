package com.example.musinsa.usecase

import com.example.musinsa.repository.UserRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    val signUpRepository: UserRepository
){
    suspend operator fun invoke(idText:String,pwText:String) = signUpRepository.signUp(idText, pwText)
}