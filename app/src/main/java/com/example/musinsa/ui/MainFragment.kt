package com.example.musinsa.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.musinsa.R
import com.example.musinsa.databinding.FragmentMainBinding
import com.example.musinsa.util.BaseFragment

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            mainLoginBtn.setOnClickListener { findNavController().navigate(R.id.loginFragment) }
            mainSignUpBtn.setOnClickListener { findNavController().navigate(R.id.signUpFragment) }
        }
    }
}