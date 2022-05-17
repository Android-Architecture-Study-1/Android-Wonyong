package com.example.musinsa

import android.os.Bundle
import androidx.activity.result.launch
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.example.musinsa.databinding.ActivityMainBinding
import com.example.musinsa.util.CustomActivityResultContract
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mainAdapter: MainAdapter
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as MainApplication).repository)
    }
    private val activityResultLauncher =
        registerForActivityResult(CustomActivityResultContract.SetName()) {
            if (it != null) mainViewModel.editName(it.first, it.second)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding?>(this, R.layout.activity_main)
            .apply {
                viewModel = mainViewModel
                lifecycleOwner = this@MainActivity
            }

        initProfile()
        initAdapter()

        binding.mainEditBtn.setOnClickListener { activityResultLauncher.launch() }
    }

    private fun initProfile() {
        Glide.with(this@MainActivity)
            .load("https://avatars.githubusercontent.com/u/82709044?v=4")
            .override(100, 100)
            .error(R.drawable.ic_launcher_foreground)
            .into(binding.mainImg)
    }

    private fun initAdapter() {
        mainAdapter = MainAdapter(this)
        binding.mainRv.adapter = mainAdapter
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.personList.collect {
                    mainAdapter.submitList(it)
                }
            }
        }
    }
}