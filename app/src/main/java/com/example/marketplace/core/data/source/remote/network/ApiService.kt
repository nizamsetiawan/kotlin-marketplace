package com.example.marketplace.core.data.source.remote.network

import com.example.marketplace.core.data.source.remote.request.LoginRequest
import com.example.marketplace.core.data.source.remote.request.RegisterRequest
import com.example.marketplace.core.data.source.remote.request.UpdateProfileRequest
import com.example.marketplace.core.data.source.remote.response.LoginResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {
    //@Headers(API)
    @POST("login")
    suspend fun login(
        @Body login : LoginRequest
    ):Response<LoginResponse>

    @POST("register")
    suspend fun register(
        @Body login : RegisterRequest
    ):Response<LoginResponse>

    @PUT("update-user/{id}")
    suspend fun update(
        @Path ("id") int: Int,
        @Body login : UpdateProfileRequest
    ):Response<LoginResponse>
@Multipart
    @POST("uploud-user/{id}")
    suspend fun uploudUser(
        @Path ("id") int: Int? = null,
       @Part data:MultipartBody.Part? = null
    ):Response<LoginResponse>


}