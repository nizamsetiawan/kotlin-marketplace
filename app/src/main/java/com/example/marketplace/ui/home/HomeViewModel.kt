package com.example.marketplace.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marketplace.core.data.source.local.DummyData
import com.example.marketplace.core.data.source.model.Category
import com.example.marketplace.core.data.source.model.Product
import com.example.marketplace.core.data.source.model.Slider

class HomeViewModel : ViewModel() {
val listCategory: LiveData<List<Category>> = MutableLiveData<List<Category>>().apply {
    value = DummyData.listCategory
}
    val listProduct: LiveData<List<Product>> = MutableLiveData<List<Product>>().apply {
        value = DummyData.listProduct
    }
        val listSlider: LiveData<List<Slider>> = MutableLiveData<List<Slider>>().apply {
            value = DummyData.listSlider

        }

}