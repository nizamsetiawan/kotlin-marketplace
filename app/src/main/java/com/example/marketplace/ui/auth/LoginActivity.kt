package com.example.marketplace.ui.auth

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.marketplace.NavigationActivity
import com.example.marketplace.core.data.source.remote.network.State
import com.example.marketplace.core.data.source.remote.request.LoginRequest
import com.example.marketplace.databinding.ActivityLoginBinding
import com.inyongtisto.myhelper.extension.dismisLoading
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.isEmpty
import com.inyongtisto.myhelper.extension.pushActivity
import com.inyongtisto.myhelper.extension.showToast
import com.inyongtisto.myhelper.extension.toastError
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginActivity : AppCompatActivity() {
    private val viewModel: AuthViewModel by viewModel()

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setData()
        mainButton()


    }

    private fun mainButton (){
        binding.btnLogin.setOnClickListener {
            login()
        }
        binding.btnDaftar.setOnClickListener {
            intentActivity(RegisterActivity::class.java)
        }
    }
    fun setData() {


    }

    private fun login() {

        if (binding.etEmail.isEmpty()) return
        if (binding.etPassword.isEmpty()) return

        val body = LoginRequest(
            binding.etEmail.text.toString(),
            binding.etPassword.text.toString()
        )

        viewModel.login(body).observe(this) {

            when (it.state){
                State.SUCCESS -> {
                    dismisLoading()
                    showToast("Selamat Datang " + it.data?.name)
                    pushActivity(NavigationActivity::class.java)
                }
                State.ERROR -> {
                    dismisLoading()
                    toastError("Periksa Kembali Email dan Password")
                }
                State.LOADING -> {
                   binding.progressBar.visibility = View.VISIBLE
                }
            }


        }
    }


}