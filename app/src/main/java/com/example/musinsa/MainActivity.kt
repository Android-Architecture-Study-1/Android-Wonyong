package com.example.musinsa

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.musinsa.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val mainViewModel: MainViewModel by viewModels()
        val mainAdapter = MainAdapter(this)

        with(binding){
            viewModel = mainViewModel
            Glide.with(this@MainActivity)
                .load("https://avatars.githubusercontent.com/u/82709044?v=4")
                .override(100, 100)
                .error(R.drawable.ic_launcher_foreground)
                .into(mainImg)

            mainRv.adapter = mainAdapter
            mainRv.layoutManager = LinearLayoutManager(this@MainActivity)
            mainViewModel.personList.observe(this@MainActivity, Observer { person->
                mainAdapter.submitList(person)
                mainAdapter.notifyDataSetChanged()
            })
        }
    }
}