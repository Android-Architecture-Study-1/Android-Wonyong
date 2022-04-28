package com.example.musinsa

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _personList: MutableLiveData<List<Person>> = MutableLiveData(emptyList())
    private val _name: MutableLiveData<String> = MutableLiveData("강")
    private val _lastName: MutableLiveData<String> = MutableLiveData("원용")

    val personList: LiveData<List<Person>> get() = _personList
    val name get() = _name
    val lastName get() = _lastName

    fun addPerson() {

    }

    fun goEditName() {

    }

    fun editName() {

    }
}