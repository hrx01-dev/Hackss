package com.runanywhere.startup_hackathon20.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.runanywhere.startup_hackathon20.database.MedicineDatabase
import com.runanywhere.startup_hackathon20.database.UserEntity
import com.runanywhere.startup_hackathon20.database.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository

    private val _currentUser = MutableStateFlow<UserEntity?>(null)
    val currentUser: StateFlow<UserEntity?> = _currentUser

    private val _userName = MutableStateFlow("User")
    val userName: StateFlow<String> = _userName

    init {
        val database = MedicineDatabase.getDatabase(application)
        val userDao = database.userDao()
        repository = UserRepository(userDao)

        loadCurrentUser()
    }

    private fun loadCurrentUser() {
        viewModelScope.launch {
            repository.loggedInUser.collectLatest { user ->
                _currentUser.value = user
                _userName.value = user?.name ?: "User"
            }
        }
    }

    fun updateUserName(newName: String) {
        viewModelScope.launch {
            _currentUser.value?.let { user ->
                repository.updateUserName(user.id, newName)
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            repository.logoutCurrentUser()
        }
    }
}
