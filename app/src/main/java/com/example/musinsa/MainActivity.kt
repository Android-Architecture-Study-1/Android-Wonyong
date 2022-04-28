package com.example.musinsa

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.musinsa.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val mainViewModel: MainViewModel by viewModels()

        with(binding){
            viewModel = mainViewModel
            Glide.with(this@MainActivity)
                .load("https://avatars.githubusercontent.com/u/82709044?v=4")
                .override(100, 100)
                .error(R.drawable.ic_launcher_foreground)
                .into(mainImg)
        }
    }
}