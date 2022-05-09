package com.example.musinsa

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel constructor(private val repository: MainRepository) : ViewModel() {
    private val _name: MutableStateFlow<String> = MutableStateFlow("강")
    private val _lastName: MutableStateFlow<String> = MutableStateFlow("원용")
    val name = _name.asStateFlow()
    val lastName = _lastName.asStateFlow()

    private val _personList: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val personList: StateFlow<UiState> = _personList.asStateFlow()

    fun addPerson() {
        viewModelScope.launch {
            repository.addPerson()
            repository.personList.collect { mPersonList ->
                _personList.update { it.copy(personList = mPersonList.toCollection(mutableListOf())) }
            }
        }
    }

    fun editName(nameText: String, lastName: String) {
        _name.value = nameText
        _lastName.value = lastName
    }
}

data class UiState(
    val personList: List<Person> = mutableListOf()
)

class MainViewModelFactory(private val repository: MainRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}