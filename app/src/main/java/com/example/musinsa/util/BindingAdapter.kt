package com.example.musinsa.util

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
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

    @BindingAdapter("bind_list")
    @JvmStatic
    fun setRecyclerView(
        recyclerView: RecyclerView,
        personList: List<Person>,
    ) {
        val adapter = getAdapter(recyclerView)
        adapter.updateItems(personList)
    }

    private fun getAdapter(recyclerView: RecyclerView): MainAdapter {
        return if(recyclerView.adapter != null && recyclerView.adapter is MainAdapter) {
            recyclerView.adapter as MainAdapter
        } else {
            val mainAdapter = MainAdapter()
            recyclerView.adapter = mainAdapter
            mainAdapter
        }
    }

    @BindingAdapter("text")
    fun setEditText(view: EditText, listener: InverseBindingListener) {
        view.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
            override fun afterTextChanged(p0: Editable?) {
                listener.onChange()
            }
        })
    }

    @InverseBindingAdapter(attribute = "text", event = "onChanged")
    fun getEditText(view: EditText): String {
        return view.text.toString()
    }
}