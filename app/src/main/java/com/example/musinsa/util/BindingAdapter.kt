package com.example.musinsa.util

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musinsa.MainAdapter
import com.example.musinsa.Person
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

    @SuppressLint("NotifyDataSetChanged")
    @JvmStatic
    @BindingAdapter("bind_list")
    fun setRecyclerView(
        recyclerView: RecyclerView,
        personList: List<Person>,
    ) {
        val adapter = recyclerView.adapter as MainAdapter
        adapter.submitList(personList)
        adapter.notifyDataSetChanged()
    }
}