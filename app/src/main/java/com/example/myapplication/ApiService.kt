package com.example.myapplication

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.Call

interface ApiService {
    @GET("/users")
    fun getUsers(): Call<List<User>>

    @POST("/users")
    fun createUser(@Body user: User): Call<User>
}