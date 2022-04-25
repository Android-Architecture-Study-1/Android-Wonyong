package com.example.musinsa.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musinsa.usecase.GetUserUseCase
import com.example.musinsa.usecase.SignUpUseCase
import com.example.musinsa.usecase.ValidEmailUseCase
import com.example.musinsa.usecase.ValidIdUseCase
import com.example.musinsa.usecase.ValidPwCheckUseCase
import com.example.musinsa.usecase.ValidPwUseCase
import com.example.musinsa.usecase.ValidSignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class UserViewModel @Inject constructor(
    val validIdUseCase: ValidIdUseCase,
    val validPwUseCase: ValidPwUseCase,
    val validPwCheckUseCase: ValidPwCheckUseCase,
    val validEmailUseCase: ValidEmailUseCase,
    val validSignUpUseCase: ValidSignUpUseCase,
    val signUpUseCase: SignUpUseCase,
    val getUserUseCase: GetUserUseCase
) : ViewModel() {
    private var _isError: MutableLiveData<Boolean> = MutableLiveData(true)
    val isError get() = _isError

    fun validId(text: String): String? = validIdUseCase(text)
    fun validPw(text: String) = validPwUseCase(text)
    fun validPwCheck(pwText: String, pwCheckText: String) = validPwCheckUseCase(pwText, pwCheckText)
    fun validEmail(text: String) = validEmailUseCase(text)
    fun signUp(idText: String, pwText: String, pwCheckText: String, emailText: String): Boolean {
        return if (validSignUpUseCase(idText, pwText, pwCheckText, emailText)) {
            viewModelScope.launch { signUpUseCase(idText, pwText) }
            true
        } else false
    }

    fun checkUser(idText: String, pwText: String) {
        viewModelScope.launch {
            getUserUseCase().collectLatest {
                isError.value = !(idText == it.id && pwText == it.pw)
            }
        }
    }
}