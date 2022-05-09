package com.example.musinsa

import android.app.Application

class MainApplication : Application() {
    private val localDataSource by lazy { LocalDataSource() }
    val repository by lazy { MainRepository(localDataSource) }
}