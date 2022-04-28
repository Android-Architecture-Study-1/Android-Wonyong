package com.example.musinsa

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musinsa.util.RandomData

class MainViewModel : ViewModel() {
    private var id = 0
    private val tempPersonList = mutableListOf<Person>()
    private val _personList: MutableLiveData<List<Person>> = MutableLiveData(emptyList())
    private val _name: MutableLiveData<String> = MutableLiveData("강")
    private val _lastName: MutableLiveData<String> = MutableLiveData("원용")

    val personList: LiveData<List<Person>> get() = _personList
    val name get() = _name
    val lastName get() = _lastName

    fun addPerson() {
        val randomName = RandomData.getName()
        val randomLastName = RandomData.getLastName()
        val randomProfile = RandomData.getProfile()
        tempPersonList.add(Person(id, randomProfile, randomName, randomLastName))
        _personList.value = tempPersonList
        id++
        Log.d("MainViewModel","temp ${tempPersonList}, live ${personList.value}")
    }

    fun goEditName() {

    }

    fun editName() {

    }
}