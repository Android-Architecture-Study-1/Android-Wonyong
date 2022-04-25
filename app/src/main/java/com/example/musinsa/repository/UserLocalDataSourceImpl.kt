package com.example.musinsa.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.musinsa.model.User
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserLocalDataSourceImpl @Inject constructor(
    @ApplicationContext val context: Context
) : UserLocalDataSource {
    private val Context.dataStore by preferencesDataStore(name = "USER")
    private val userId = stringPreferencesKey(USER_ID)
    private val userPw = stringPreferencesKey(USER_PW)

    override suspend fun signUp(user: User) {
        context.dataStore.edit { preferences ->
            preferences[userId] = user.id
            preferences[userPw] = user.pw
        }
    }

    override suspend fun getUser(): Flow<User> {
        val user: Flow<User> = context.dataStore.data.map { preferences ->
            val id = preferences[userId] ?: ""
            val pw = preferences[userPw] ?: ""
            User(id, pw)
        }
        return user
    }

    companion object {
        const val USER_ID = "user_id"
        const val USER_PW = "user_pw"
    }
}