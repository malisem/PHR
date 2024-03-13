package com.project.phr.repository.firebaseImpl

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.project.phr.model.User
import com.project.phr.repository.AuthRepository
import com.project.phr.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AuthRepositoryFirebase : AuthRepository {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val userRef = FirebaseFirestore.getInstance().collection("users")

    override suspend fun currentUser(): Resource<User> {
        return withContext(Dispatchers.IO) {
            try {
                val currentUser = firebaseAuth.currentUser
                if (currentUser != null) {
                    val userDoc = userRef.document(currentUser.uid).get().await()
                    val user = userDoc.toObject(User::class.java)
                    if (user != null) {
                        Resource.Success(user)
                    } else {
                        Resource.Error("User data not found")
                    }
                } else {
                    Resource.Error("No current user found")
                }
            } catch (e: Exception) {
                Resource.Error("Error fetching current user: ${e.message}")
            }
        }
    }

    override suspend fun login(email: String, password: String): Resource<User> {
        return withContext(Dispatchers.IO) {
            try {
                val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
                val userId = result.user?.uid
                if (userId != null) {
                    val userDoc = userRef.document(userId).get().await()
                    val user = userDoc.toObject(User::class.java)
                    if (user != null) {
                        Resource.Success(user)
                    } else {
                        Resource.Error("Failed to load user data")
                    }
                } else {
                    Resource.Error("User ID is null")
                }
            } catch (e: Exception) {
                Resource.Error("Login failed: ${e.message}")
            }
        }
    }

    override suspend fun createUser(
        userName: String,
        userEmail: String,
        userPhone: String,
        userLoginPass: String
    ): Resource<User> {
        return withContext(Dispatchers.IO) {
            try {
                val registrationResult = firebaseAuth.createUserWithEmailAndPassword(userEmail, userLoginPass).await()
                val userId = registrationResult.user?.uid
                if (userId != null) {
                    val newUser = User(userName, userEmail, userPhone)
                    userRef.document(userId).set(newUser).await()
                    Resource.Success(newUser)
                } else {
                    Resource.Error("User ID is null after registration")
                }
            } catch (e: Exception) {
                Resource.Error("User registration failed: ${e.message}")
            }
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }
}
