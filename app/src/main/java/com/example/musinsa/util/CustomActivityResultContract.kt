package com.example.musinsa.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.example.musinsa.EditActivity

object CustomActivityResultContract {
    class SetName : ActivityResultContract<Void, Pair<String, String>?>() {
        override fun createIntent(context: Context, input: Void?): Intent =
            Intent(context, EditActivity::class.java)

        override fun parseResult(resultCode: Int, intent: Intent?): Pair<String, String>? {
            return if (resultCode == Activity.RESULT_OK) {
                val name = intent?.extras?.getString("name") ?: ""
                val lastName = intent?.extras?.getString("lastName") ?: ""
                Pair(name, lastName)
            } else null
        }
    }
}