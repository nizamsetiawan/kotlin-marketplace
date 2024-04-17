package com.example.marketplace.ui.updateprofile

import android.app.Activity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.marketplace.core.data.source.remote.network.State
import com.example.marketplace.core.data.source.remote.request.UpdateProfileRequest
import com.example.marketplace.databinding.ActivityUpdateProfileBinding
import com.example.marketplace.ui.auth.AuthViewModel
import com.example.marketplace.ui.base.MyActivity
import com.example.marketplace.util.Constants
import com.example.marketplace.util.Prefs
import com.github.drjacky.imagepicker.ImagePicker
import com.github.drjacky.imagepicker.constant.ImageProvider
import com.inyongtisto.myhelper.extension.getInitial
import com.inyongtisto.myhelper.extension.int
import com.inyongtisto.myhelper.extension.isEmpty
import com.inyongtisto.myhelper.extension.setToolbar
import com.inyongtisto.myhelper.extension.toMultipartBody
import com.inyongtisto.myhelper.extension.toastError
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File


class UpdateProfileActivity : MyActivity() {
    private val viewModel: AuthViewModel by viewModel()

    private var _binding: ActivityUpdateProfileBinding? = null
    private val binding get() = _binding!!
    private var fileImage: File? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUpdateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar(binding.toolbar, "Update Profile")
        mainButton()
        setData()


    }

    private fun mainButton() {

        binding.btnSimpan.setOnClickListener {
            if (fileImage != null){
                uploud()
            } else{
                update()
            }


        }
        binding.imageProfile.setOnClickListener{
            pickImage()
        }
    }
    private fun pickImage(){
        ImagePicker.with(this)
            .crop()
            .maxResultSize(1080, 1080, true) //true: Keep Ratio
            .provider(ImageProvider.BOTH) //Or bothCameraGallery()
            .createIntentFromDialog { launcher.launch(it) }

    }
    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val uri = it.data?.data!!
                fileImage = File(uri.path?:"")
                // Use the uri to load the image
                Picasso.get().load(uri).into(binding.imageProfile)
                // Only if you are not using crop feature:
//                uri?.let { galleryUri ->
//                    contentResolver.takePersistableUriPermission(
//                        uri, binding.imageProfile
//                    )
//                }
                //////////////

            }
        }

    private fun setData() {
        val user = Prefs.getUser()
        if (user != null) {
            binding.apply {
                etNamalengkap.setText(user.name)
                etEmail.setText(user.email)
                etPhone.setText(user.phone)
                tvInisial.text = user.name.getInitial()
                Picasso.get().load(Constants.STORAGE_URL + user.image).into(binding.imageProfile)
            }
        }
    }

    private fun update() {
        if (binding.etNamalengkap.isEmpty()) return
        if (binding.etEmail.isEmpty()) return
        if (binding.etPhone.isEmpty()) return

        val idUser = Prefs.getUser()?.id
        val body = UpdateProfileRequest(
            idUser.int(),
            binding.etNamalengkap.text.toString(),
            binding.etEmail.text.toString(),
            binding.etPhone.text.toString(),
        )

        viewModel.updateUser(body).observe(this) {

            when (it.state){
                State.SUCCESS -> {
                    progress.dismiss()
                    onBackPressed()
                }
                State.ERROR -> {
                    progress.dismiss()
                    toastError("Periksa Kembali Email dan Password")
                }
                State.LOADING -> {
                    progress.show()
                }
            }


        }
    }

    private fun uploud(){
        val idUser = Prefs.getUser()?.id
        val file = fileImage.toMultipartBody() //default image jika file image maka dalam kurung fileimage
        viewModel.uploudUser(idUser, file).observe(this) {

            when (it.state){
                State.SUCCESS -> {
                    progress.dismiss()
                    update()
                }
                State.ERROR -> {
                    progress.dismiss()
                    toastError("Periksa Kembali Email dan Password")
                }
                State.LOADING -> {
                    progress.show()
                }
            }


        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}