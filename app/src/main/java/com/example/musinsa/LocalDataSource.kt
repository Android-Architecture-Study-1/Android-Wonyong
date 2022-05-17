package com.example.musinsa

class LocalDataSource {
    private val personList: MutableList<Person> = mutableListOf()

    fun getPersonList(): MutableList<Person> = personList

    fun addPerson(person: Person) = personList.add(person)
}