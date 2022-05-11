package com.example.musinsa


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musinsa.databinding.PersonFrameBinding

class MainAdapter(private val context: Context) :
    ListAdapter<Person, MainAdapter.PersonViewHolder>(PERSON_COMPARATOR) {

    inner class PersonViewHolder(binding: PersonFrameBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val name = binding.personName
        private val lastName = binding.personLastName
        private val profile = binding.personImg

        fun bind(person: Person) {
            Glide.with(context)
                .load(person.profile)
                .override(100, 100)
                .error(R.drawable.ic_launcher_foreground)
                .into(profile)
            name.text = person.name
            lastName.text = person.lastName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = PersonFrameBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val current = getItem(position)
        with(holder) {
            bind(current)
        }
    }

    companion object {
        private val PERSON_COMPARATOR = object : DiffUtil.ItemCallback<Person>() {
            override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }
}