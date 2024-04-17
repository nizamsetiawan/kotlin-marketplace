package com.example.marketplace.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.marketplace.core.data.repository.AppRepository
import com.example.marketplace.core.data.source.remote.request.LoginRequest
import com.example.marketplace.core.data.source.remote.request.RegisterRequest
import com.example.marketplace.core.data.source.remote.request.UpdateProfileRequest
import okhttp3.MultipartBody

class AuthViewModel(val repo: AppRepository ) : ViewModel() {


    fun login(data : LoginRequest) = repo.login(data ).asLiveData()
    fun register(data : RegisterRequest) = repo.register(data ).asLiveData()
    fun updateUser(data : UpdateProfileRequest) = repo.update(data ).asLiveData()
    fun uploudUser(id: Int? = null, fileImage: MultipartBody.Part? = null) = repo.uploudUser(id, fileImage).asLiveData()




}