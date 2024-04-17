package com.example.marketplace.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.marketplace.NavigationActivity
import com.example.marketplace.databinding.FragmentProfileBinding
import com.example.marketplace.ui.updateprofile.UpdateProfileActivity
import com.example.marketplace.util.Constants.STORAGE_URL
import com.example.marketplace.util.Prefs
import com.inyongtisto.myhelper.extension.getInitial
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.pushActivity
import com.squareup.picasso.Picasso

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
mainButton()
        return root
    }

    override fun onResume() {
        setUser()
        super.onResume()
    }

    private fun mainButton() {
        binding.btnLogout.setOnClickListener {
            Prefs.isLogin = false
            pushActivity(NavigationActivity::class.java)
        }
        binding.btnUpdate.setOnClickListener{
            intentActivity(UpdateProfileActivity::class.java)
        }
    }

    private fun setUser() {
        val user = Prefs.getUser()
       if (user != null) {
          binding.apply {
                tvName.text = user.name
                tvEmail.text = user.email
                tvPhone.text = user.phone
              tvInisial.text = user.name?.getInitial()
              Picasso.get().load(STORAGE_URL + user.image).into(binding.imageProfile)
          }
       }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}