package com.example.musinsa.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.musinsa.R
import com.example.musinsa.databinding.FragmentLoginBinding
import com.example.musinsa.util.BaseFragment

class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {
    private val viewModel: UserViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            loginBtn.setOnClickListener {
                viewModel.checkUser(loginIdEt.text.toString(), loginPwEt.text.toString())
                viewModel.isError.observe(viewLifecycleOwner, Observer { isError ->
                    if (isError) Toast.makeText(context, getString(R.string.retry_msg), Toast.LENGTH_SHORT).show()
                    else findNavController().popBackStack()
                })
            }
            loginSignUpBtn.setOnClickListener { findNavController().navigate(R.id.signUpFragment) }
            loginToolBar.setNavigationOnClickListener { findNavController().popBackStack() }
        }
    }
}