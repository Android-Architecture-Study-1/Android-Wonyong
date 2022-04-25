package com.example.musinsa.usecase

import android.util.Patterns
import javax.inject.Inject

class ValidEmailUseCase @Inject constructor() {
    operator fun invoke(text: String): String? {
        return when {
            text.isEmpty() -> "이메일은 필수정보입니다."
            !Patterns.EMAIL_ADDRESS.matcher(text).matches() -> "이메일 주소가 올바르지 않습니다."
            else -> null
        }
    }
}