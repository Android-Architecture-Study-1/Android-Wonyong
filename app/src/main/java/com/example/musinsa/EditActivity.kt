package com.example.musinsa

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.musinsa.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityEditBinding?>(this, R.layout.activity_edit)
            .apply {
                vm = viewModel
                lifecycleOwner = this@EditActivity
                editMain = this@EditActivity
            }
    }

    fun editName() {
        val intent = Intent(this@EditActivity, MainActivity::class.java).apply {
            putExtra("name", viewModel.name.value)
            putExtra("lastName", viewModel.lastName.value)
        }
        setResult(RESULT_OK, intent)
        finish()
    }
}