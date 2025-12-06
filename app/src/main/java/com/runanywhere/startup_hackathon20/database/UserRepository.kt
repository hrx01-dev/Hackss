package com.runanywhere.startup_hackathon20.database

import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    val loggedInUser: Flow<UserEntity?> = userDao.getLoggedInUser()

    suspend fun getLoggedInUserSync(): UserEntity? {
        return userDao.getLoggedInUserSync()
    }

    suspend fun registerUser(name: String, username: String, password: String): Long {
        // Logout all users first (single user system)
        userDao.logoutAllUsers()

        // Create and insert new user
        val user = UserEntity(
            name = name,
            username = username,
            password = password,
            isLoggedIn = true
        )
        return userDao.insertUser(user)
    }

    suspend fun loginUser(username: String, password: String): Boolean {
        val user = userDao.getUserByUsername(username)
        return if (user != null && user.password == password) {
            userDao.logoutAllUsers()
            userDao.loginUser(user.id)
            true
        } else {
            false
        }
    }

    suspend fun logoutCurrentUser() {
        userDao.logoutAllUsers()
    }

    suspend fun updateUserName(userId: Long, newName: String) {
        val user = userDao.getLoggedInUserSync()
        if (user != null && user.id == userId) {
            userDao.updateUser(user.copy(name = newName))
        }
    }

    suspend fun deleteUser(userId: Long) {
        userDao.deleteUser(userId)
    }
}
