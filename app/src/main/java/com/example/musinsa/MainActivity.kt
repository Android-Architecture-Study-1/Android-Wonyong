package com.example.musinsa

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.musinsa.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding?>(this, R.layout.activity_main)
            .apply {
                viewModel = mainViewModel
                lifecycleOwner = this@MainActivity
                main = this@MainActivity
            }
        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    val name = it.data?.getStringExtra("name") ?: ""
                    val lastName = it.data?.getStringExtra("lastName") ?: ""
                    mainViewModel.editName(name, lastName)
                }
            }
    }

    fun goEditName() {
        activityResultLauncher.launch(
            Intent(
                this@MainActivity,
                EditActivity::class.java
            )
        )
    }
}