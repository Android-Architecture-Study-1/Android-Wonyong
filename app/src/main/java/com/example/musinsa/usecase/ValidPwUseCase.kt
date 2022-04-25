package com.example.musinsa.usecase

import javax.inject.Inject

class ValidPwUseCase @Inject constructor() {
    operator fun invoke(text: String): String? {
        return when {
            text.isEmpty() -> "비밀번호는 필수정보입니다."
            text.length < 8 || text.length > 30 -> "8~30자 이내로 입력해 주십시오."
            else -> null
        }
    }
}