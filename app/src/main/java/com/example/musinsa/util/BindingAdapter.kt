package com.example.musinsa.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.musinsa.R

object BindingAdapter {
    @BindingAdapter("name")
    @JvmStatic
    fun setName(textView: TextView, name: String) {
        textView.text = String.format("성: %s", name)
    }

    @BindingAdapter("lastName")
    @JvmStatic
    fun setLastName(textView: TextView, name: String) {
        textView.text = String.format("이름: %s", name)
    }

    @BindingAdapter("profile")
    @JvmStatic
    fun setProfile(imageView: ImageView, profile: String) {
        Glide.with(imageView.context)
            .load(profile)
            .override(100, 100)
            .error(R.drawable.ic_launcher_foreground)
            .into(imageView)
    }
}