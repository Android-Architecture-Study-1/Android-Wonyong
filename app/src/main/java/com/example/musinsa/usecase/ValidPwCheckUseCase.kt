package com.example.musinsa.usecase

import javax.inject.Inject

class ValidPwCheckUseCase @Inject constructor() {
    operator fun invoke(pwText: String, pwCheckText: String): String? {
        return when {
            pwCheckText.isEmpty() -> "비밀번호 재확인은 필수정보입니다."
            pwText != pwCheckText -> "비밀번호가 일치하지 않습니다."
            else -> null
        }
    }
}