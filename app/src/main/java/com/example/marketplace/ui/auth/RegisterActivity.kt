package com.example.marketplace.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.marketplace.core.data.source.remote.network.State
import com.example.marketplace.core.data.source.remote.request.RegisterRequest
import com.example.marketplace.databinding.ActivityRegisterBinding
import com.inyongtisto.myhelper.extension.dismisLoading
import com.inyongtisto.myhelper.extension.isEmpty
import com.inyongtisto.myhelper.extension.pushActivity
import com.inyongtisto.myhelper.extension.showLoading
import com.inyongtisto.myhelper.extension.showToast
import com.inyongtisto.myhelper.extension.toastError
import org.koin.androidx.viewmodel.ext.android.viewModel


class RegisterActivity : AppCompatActivity() {
    private val viewModel: AuthViewModel by viewModel()

    private var _binding: ActivityRegisterBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setData()


    }

    fun setData() {

        binding.btnDaftar.setOnClickListener {
            register()
        }
    }

    private fun register() {
        if (binding.etNamalengkap.isEmpty()) return
        if (binding.etEmail.isEmpty()) return
        if (binding.etPhone.isEmpty()) return
        if (binding.etPassword.isEmpty()) return

        val body = RegisterRequest(
            binding.etNamalengkap.text.toString(),
            binding.etEmail.text.toString(),
            binding.etPhone.text.toString(),
            binding.etPassword.text.toString()
        )

        viewModel.register(body).observe(this) {

            when (it.state){
                State.SUCCESS -> {
                    dismisLoading()
                    showToast("Register anda berhasil " + it.data?.name)
                    pushActivity(LoginActivity::class.java)
                }
                State.ERROR -> {
                    dismisLoading()
                    toastError("Periksa Kembali Email dan Password")
                }
                State.LOADING -> {
                    showLoading()
                }
            }


        }
    }


}