package com.salvatour.data.api

import com.salvatour.data.model.LoginModel
import com.salvatour.data.model.LoginResponse
import com.salvatour.data.model.PostModel
import com.salvatour.data.model.RegisterData
import com.salvatour.data.model.RegisterResponse
import com.salvatour.data.model.UserModel
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query


interface ApiService {

    @Headers(value = ["Content-Type: application/json"])
    @POST(value = "/api/auth/register")
    suspend fun register(@Body data: RegisterData) : RegisterResponse
    @Headers("Content-Type: application/json")
    @GET(value = "/api/auth/login")
    suspend fun login(
        @Query("username") username: String,
        @Query("password") password: String
    ): LoginResponse

    @Headers(value = ["Content-Type: application/json"])
    @GET(value = "/api/auth/whoami")
    suspend fun whoami(
        @Header("Authorization") token: String
    ): UserModel

    @Headers(value = ["Content-Type: application/json"])
    @PUT("/api/auth/update")
    suspend fun updateUser(
        @Header("Authorization") token: String,
        @Body user: UserModel
    ): UserModel

    @Headers(value = ["Content-Type: application/json"])
    @POST("/api/post/")
    suspend fun postTour(
        @Header("Authorization") token: String,
        @Body tour: PostModel
    ): PostModel

    @Headers(value = ["Content-Type: application/json"])
    @GET("/api/post/")
    suspend fun getAllTours(
        @Header("Authorization") token: String
    ): List<PostModel>

    @Headers(value = ["Content-Type: application/json"])
    @DELETE("/api/post/")
    suspend fun deleteTour(
        @Header("Authorization") token: String,
        @Query("id") id: String
        ): PostModel

    @Headers(value = ["Content-Type: application/json"])
    @GET("/api/post/")
    suspend fun findByUserTour(
        @Header("Authorization") token: String,
        @Query("id") id: String
        ): List<PostModel>
}