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
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val mainViewModel: MainViewModel by viewModels()
        val mainAdapter = MainAdapter(this)

        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    val name = it.data?.getStringExtra("name") ?: ""
                    val lastName = it.data?.getStringExtra("lastName") ?: ""
                    mainViewModel.editName(name, lastName)
                }
            }

        with(binding) {
            viewModel = mainViewModel
            binding.lifecycleOwner = this@MainActivity
            Glide.with(this@MainActivity)
                .load("https://avatars.githubusercontent.com/u/82709044?v=4")
                .override(100, 100)
                .error(R.drawable.ic_launcher_foreground)
                .into(mainImg)

            mainRv.adapter = mainAdapter
            mainRv.layoutManager = LinearLayoutManager(this@MainActivity)
            mainViewModel.personList.observe(this@MainActivity, Observer { person ->
                mainAdapter.submitList(person)
                mainAdapter.notifyDataSetChanged()
            })

            mainEditBtn.setOnClickListener {
                val intent = Intent(
                    this@MainActivity,
                    EditActivity::class.java
                )
                activityResultLauncher.launch(intent)
            }
        }
    }
}