package com.project.phr.repository

import com.project.phr.model.User
import com.project.phr.util.Resource

interface AuthRepository {

    suspend fun currentUser() : Resource<User>
    suspend fun login(email:String, password:String) : Resource<User>
    suspend fun createUser(userName:String,
                           userEmail:String,
                           userPhone:String,
                           userLoginPass:String) : Resource<User>
    fun logout()
}