package com.example.musinsa.util

import android.widget.TextView
import androidx.databinding.BindingAdapter

object BindingAdapter {
    @BindingAdapter("name")
    @JvmStatic
    fun setName(textView: TextView, name:String){
        textView.text = String.format("성: %s",name)
    }

    @BindingAdapter("lastName")
    @JvmStatic
    fun setLastName(textView: TextView, name:String){
        textView.text = String.format("이름: %s",name)
    }
}