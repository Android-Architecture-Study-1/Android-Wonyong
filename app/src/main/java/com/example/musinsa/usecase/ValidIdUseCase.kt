package com.example.musinsa.usecase

import javax.inject.Inject

class ValidIdUseCase @Inject constructor() {
    operator fun invoke(text: String): String? {
        return when {
            text.isEmpty() -> "아이디는 필수정보 입니다."
            text.length < 5 -> "아이디는 5자 이상이어야 합니다."
            else -> null
        }
    }
}