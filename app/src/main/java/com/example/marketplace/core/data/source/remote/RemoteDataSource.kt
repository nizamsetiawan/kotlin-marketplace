package com.example.marketplace.core.data.source.remote

import com.example.marketplace.core.data.source.remote.network.ApiService
import com.example.marketplace.core.data.source.remote.request.LoginRequest
import com.example.marketplace.core.data.source.remote.request.RegisterRequest
import com.example.marketplace.core.data.source.remote.request.UpdateProfileRequest
import okhttp3.MultipartBody

class RemoteDataSource(private val api : ApiService) {
    suspend fun login(data : LoginRequest) = api.login(data)
    suspend fun register(data : RegisterRequest) = api.register(data)
    suspend fun updateUser(data : UpdateProfileRequest) = api.update(data.id, data)
    suspend fun uploudUser(id: Int? = null, fileImage: MultipartBody.Part? = null) = api.uploudUser(id, fileImage)
}