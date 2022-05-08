package com.example.musinsa

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.musinsa.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mainAdapter: MainAdapter
    private val mainViewModel: MainViewModel by viewModels()
    private val activityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val name = it.data?.getStringExtra("name") ?: ""
                val lastName = it.data?.getStringExtra("lastName") ?: ""
                mainViewModel.editName(name, lastName)
            }
        }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding?>(this, R.layout.activity_main).apply {
            viewModel = mainViewModel
            lifecycleOwner = this@MainActivity
        }

        initProfile()
        initAdapter()

        binding.mainEditBtn.setOnClickListener {
            val intent = Intent(
                this@MainActivity,
                EditActivity::class.java
            )
            activityResultLauncher.launch(intent)
        }
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
        mainViewModel.personList.observe(this@MainActivity, Observer { person ->
            mainAdapter.submitList(person)
            mainAdapter.notifyDataSetChanged()
        })
    }
}