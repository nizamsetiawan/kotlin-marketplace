package com.example.marketplace.core.data.repository

import com.example.marketplace.core.data.source.local.LocalDataSource
import com.example.marketplace.core.data.source.remote.RemoteDataSource
import com.example.marketplace.core.data.source.remote.network.Resource
import com.example.marketplace.core.data.source.remote.request.LoginRequest
import com.example.marketplace.core.data.source.remote.request.RegisterRequest
import com.example.marketplace.core.data.source.remote.request.UpdateProfileRequest
import com.example.marketplace.util.Prefs
import com.inyongtisto.myhelper.extension.logs
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody

class AppRepository (val localDataSource: LocalDataSource, val remoteDataSource: RemoteDataSource) {

    fun login(data : LoginRequest) = flow{
        emit(Resource.loading(null))
        try {
            remoteDataSource.login(data).let {
                if (it.isSuccessful) {
                    Prefs.isLogin = true
                   val body  = it.body()
                    val user = body?.data
                    Prefs.setUser(user)
                    emit (Resource.success(user))
                    logs("AppRepository Success :" , body.toString() )
                } else {
                    emit(Resource.error(it.message(), null))
                   logs("AppRepository Error :" , it.message())
                }
            }
        } catch (e: Exception) {
            logs("AppRepository Error :" , e.message.toString())
        }

    }
    fun register(data : RegisterRequest) = flow{
        emit(Resource.loading(null))
        try {
            remoteDataSource.register(data).let {
                if (it.isSuccessful) {
                    Prefs.isLogin = true
                    val body  = it.body()
                    val user = body?.data
                    Prefs.setUser(user)
                    emit (Resource.success(user))
                    logs("Register Berhasil :" , body.toString() )
                } else {
                    emit(Resource.error(it.message(), null))
                    logs("Register Gagal :" , it.message())
                }
            }
        } catch (e: Exception) {
            logs("AppRepository Error :" , e.message.toString())
        }

    }

    fun update(data : UpdateProfileRequest) = flow{
        emit(Resource.loading(null))
        try {
            remoteDataSource.updateUser(data).let {
                if (it.isSuccessful) {
                    val body  = it.body()
                    val user = body?.data
                    Prefs.setUser(user)
                    emit (Resource.success(user))
                } else {
                    emit(Resource.error(it.message(), null))
                }
            }
        } catch (e: Exception) {
            logs("AppRepository Error :" , e.message.toString())
        }

    }

    fun uploudUser(id: Int? = null, fileImage: MultipartBody.Part? = null) = flow{
        emit(Resource.loading(null))
        try {
            remoteDataSource.uploudUser(id, fileImage).let {
                if (it.isSuccessful) {
                    val body  = it.body()
                    val user = body?.data
                    Prefs.setUser(user)
                    emit (Resource.success(user))
                } else {
                    emit(Resource.error(it.message(), null))
                }
            }
        } catch (e: Exception) {
            logs("AppRepository Error :" , e.message.toString())
        }

    }

}