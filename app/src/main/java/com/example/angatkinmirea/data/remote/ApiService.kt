package com.example.angatkinmirea.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse

    @GET("users")
    suspend fun getUsers(): UsersResponse

    @GET("users/{id}")
    suspend fun getUserById(
        @Path("id") id: Int
    ): UserDto
}