package com.example.marketplace.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace.core.data.source.model.Category
import com.example.marketplace.databinding.ItemHomeCategoryBinding
@SuppressLint("NotifyDataSetChanged")
class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.viewHolder>() {
    private var data = ArrayList<Category>()
    inner class viewHolder( private var itemBinding: ItemHomeCategoryBinding):RecyclerView.ViewHolder(itemBinding.root){
        fun bind(item: Category, position: Int){
            itemBinding.apply {
                tvName.text = item.name
                imageView.setImageResource(item.image)
            }
        }
    }

    fun addItems(items: List<Category>){
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(ItemHomeCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
       holder.bind(data[position], position)
    }
}