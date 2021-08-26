package com.proway.crud.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.proway.crud.R
import com.proway.crud.databinding.CategoryItemLayoutBinding
import com.proway.crud.model.Category

class CategoryAdapter(val onTap: (Category) -> Unit) : RecyclerView.Adapter<CategoryViewHolder>() {

    private var listOf = mutableListOf<Category>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.category_item_layout, parent, false).let {
                CategoryViewHolder(it, onTap)
            }
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        listOf[position].let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = listOf.size

    fun refresh(newList: List<Category>) {
        listOf = mutableListOf()
        listOf.addAll(newList)
        notifyDataSetChanged()
    }
}

class CategoryViewHolder(itemView: View, val onTap: (Category) -> Unit) : RecyclerView.ViewHolder(itemView) {

    private val binding = CategoryItemLayoutBinding.bind(itemView)

    fun bind(category: Category) {
        binding.idTextView.text = category.id.toString()
        binding.nameTextView.text = category.name
        itemView.setOnClickListener { onTap(category) }
    }

}