package com.example.musinsa.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.musinsa.R
import com.example.musinsa.databinding.FragmentSignUpBinding
import com.example.musinsa.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding>(R.layout.fragment_sign_up) {
    private val viewModel: UserViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        idChecker()
        pwChecker()
        pwCheckChecker()
        emailChecker()
        signUp()
        binding.signUpToolBar.setNavigationOnClickListener { findNavController().popBackStack() }
    }

    private fun idChecker() {
        with(binding) {
            signUpIdEt.doOnTextChanged { _, _, _, _ ->
                signUpIdTl.error = viewModel.validId(signUpIdEt.text.toString())
            }
        }
    }

    private fun pwChecker() {
        with(binding) {
            signUpPwEt.doOnTextChanged { _, _, _, _ ->
                signUpPwTl.error = viewModel.validPw(signUpPwEt.text.toString())
            }
        }
    }

    private fun pwCheckChecker() {
        with(binding) {
            signUpPwCheckEt.doOnTextChanged { _, _, _, _ ->
                signUpPwCheckTl.error = viewModel.validPwCheck(
                    signUpPwEt.text.toString(),
                    signUpPwCheckEt.text.toString()
                )
            }
        }
    }

    private fun emailChecker() {
        with(binding) {
            signUpEmailEt.doOnTextChanged { _, _, _, _ ->
                signUpEmailTl.error =
                    viewModel.validEmail(signUpEmailEt.text.toString())
            }
        }
    }

    private fun signUp() {
        with(binding) {
            signUpBtn.setOnClickListener {
                if (viewModel.signUp(
                        signUpIdEt.text.toString(),
                        signUpPwEt.text.toString(),
                        signUpPwCheckEt.text.toString(),
                        signUpEmailEt.text.toString()
                    )
                ) {
                    findNavController().popBackStack()
                } else {
                    Toast.makeText(context, getString(R.string.retry_msg), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}