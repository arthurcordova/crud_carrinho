package com.proway.crud.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.proway.crud.R
import com.proway.crud.databinding.ProductItemLayoutBinding
import com.proway.crud.model.ProductWithCategory

class ProductAdapter(private val onTap: (ProductWithCategory) -> Unit) :
    RecyclerView.Adapter<ProductAdapterViewHolder>() {

    private var listOf = mutableListOf<ProductWithCategory>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapterViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item_layout, parent, false).let {
                ProductAdapterViewHolder(it, onTap)
            }
    }

    override fun onBindViewHolder(holder: ProductAdapterViewHolder, position: Int) {
        listOf[position].let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = listOf.size

    fun refresh(newList: List<ProductWithCategory>) {
        listOf = mutableListOf()
        listOf.addAll(newList)
        notifyDataSetChanged()
    }
}

class ProductAdapterViewHolder(itemView: View, val onTap: (ProductWithCategory) -> Unit) :
    RecyclerView.ViewHolder(itemView) {

    private val binding = ProductItemLayoutBinding.bind(itemView)

    fun bind(model: ProductWithCategory) {
        binding.idTextView.text = model.product?.id.toString()
        binding.nameTextView.text = model.product?.name
        binding.priceTextView.text = model.product?.price.toString()
        binding.categoryTextView.text = model.category?.name
        itemView.setOnClickListener { onTap(model) }
    }

}