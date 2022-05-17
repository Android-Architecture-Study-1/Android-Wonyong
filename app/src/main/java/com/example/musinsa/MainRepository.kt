package com.example.musinsa

import com.example.musinsa.util.RandomData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MainRepository(private val localDataSource: LocalDataSource) {
    private var id = 0
    val personList: Flow<MutableList<Person>> = flowOf(localDataSource.getPersonList())

    fun addPerson() {
        val randomName = RandomData.getName()
        val randomLastName = RandomData.getLastName()
        val randomProfile = RandomData.getProfile()
        localDataSource.addPerson(Person(id, randomProfile, randomName, randomLastName))
        id++
    }
}