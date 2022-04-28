package com.example.musinsa

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.musinsa.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit)

        with(binding) {
            editBtn.setOnClickListener {
                val intent = Intent(this@EditActivity, MainActivity::class.java).apply {
                    putExtra("name", nameEt.text.toString())
                    putExtra("lastName", lastNameEt.text.toString())
                }
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }
}