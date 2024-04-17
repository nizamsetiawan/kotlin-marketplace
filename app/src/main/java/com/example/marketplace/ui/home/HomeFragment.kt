package com.example.marketplace.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.marketplace.databinding.FragmentHomeBinding
import com.example.marketplace.ui.home.adapter.CategoryAdapter
import com.example.marketplace.ui.home.adapter.ProdukTerbaruAdapter
import com.example.marketplace.ui.home.adapter.ProdukTerlarisAdapter
import com.example.marketplace.ui.home.adapter.SliderAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var viewModel: HomeViewModel
    private val binding get() = _binding!!
    private val adapterCategory = CategoryAdapter()
    private val adapterSlider = SliderAdapter()
    private val adapterProductTerlaris = ProdukTerlarisAdapter()
    private val adapterProductTerbaru = ProdukTerbaruAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setUpAdapter()
        setData()
        mainButton()
        return root
    }

    private fun setUpAdapter(){
        binding.rvCategory.adapter = adapterCategory
        binding.rvSlider.adapter = adapterSlider
        binding.rvProductTerlaris.adapter = adapterProductTerlaris
        binding.rvProductTerbaru.adapter = adapterProductTerbaru
    }
    private fun mainButton() {

    }

    private fun setData() {
        viewModel.listCategory.observe(requireActivity()) {
            adapterCategory.addItems(it)
        }
        viewModel.listSlider.observe(requireActivity()) {
            adapterSlider.addItems(it)
        }
        viewModel.listProduct.observe(requireActivity()){
            adapterProductTerlaris.addItems(it)
            adapterProductTerbaru.addItems(it)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}