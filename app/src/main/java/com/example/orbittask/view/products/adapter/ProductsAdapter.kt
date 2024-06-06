package com.example.orbittask.view.products.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.orbittask.data.models.Product
import com.example.orbittask.databinding.ItemProductBinding

class ProductsAdapter(private val onItemClickListener: (Product) -> Unit) :
    ListAdapter<Product, ProductsAdapter.MyViewHolder>(
        ProductDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClickListener)
    }

    class MyViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product, onItemClickListener: (Product) -> Unit) {
            binding.apply {

                Glide.with(root.context).load(product.thumbnail).into(ivProduct)
                tvName.text = product.title
                tvPrice.text = "${product.price}$"

                layout.setOnClickListener {
                    onItemClickListener(product)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemProductBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }
    }

    class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
}