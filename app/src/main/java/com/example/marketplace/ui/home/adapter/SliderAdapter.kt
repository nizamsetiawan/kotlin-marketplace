package com.example.marketplace.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace.core.data.source.model.Slider
import com.example.marketplace.databinding.ItemHomeSliderBinding

@SuppressLint("NotifyDataSetChanged")
class SliderAdapter : RecyclerView.Adapter<SliderAdapter.viewHolder>() {
    private var data = ArrayList<Slider>()
    inner class viewHolder( private var itemBinding: ItemHomeSliderBinding):RecyclerView.ViewHolder(itemBinding.root){
        fun bind(item: Slider, position: Int){
            itemBinding.apply {
                imageView.setImageResource(item.image)
            }
        }
    }

    fun addItems(items: List<Slider>){
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(ItemHomeSliderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
       holder.bind(data[position], position)
    }
}