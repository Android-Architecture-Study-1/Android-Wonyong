package com.example.musinsa.usecase

import javax.inject.Inject

class ValidSignUpUseCase @Inject constructor(
    val validIdUseCase: ValidIdUseCase,
    val validPwUseCase: ValidPwUseCase,
    val validPwCheckUseCase: ValidPwCheckUseCase,
    val validEmailUseCase: ValidEmailUseCase,
) {
    operator fun invoke(
        idText: String,
        pwText: String,
        pwCheckText: String,
        emailText: String
    ): Boolean {
        return validIdUseCase(idText) == null &&
                validPwUseCase(pwText) == null &&
                validPwCheckUseCase(pwText, pwCheckText) == null &&
                validEmailUseCase(emailText) == null
    }
}