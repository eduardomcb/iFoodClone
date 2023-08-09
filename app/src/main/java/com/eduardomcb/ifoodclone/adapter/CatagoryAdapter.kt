package com.eduardomcb.ifoodclone.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eduardomcb.ifoodclone.model.Category
import com.eduardomcb.ifoodclone.databinding.CategoryItemBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class CatagoryAdapter(
    private val context: Context,
    private val categoryList: MutableList<Category>
) :
    RecyclerView.Adapter<CatagoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemList = CategoryItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return CategoryViewHolder(itemList)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, @SuppressLint("RecyclerView") position: Int) {
        Picasso.get().load(categoryList[position].logoUrl).into(holder.imgCategory, object : Callback {
            override fun onSuccess() {
                val shape = GradientDrawable()
                shape.cornerRadius = 10f
                shape.setColor(categoryList[position].color.toInt())

                holder.bgCategory.background = shape
            }

            override fun onError(e: Exception?) {
                TODO("Not yet implemented")
            }

        })

        holder.txtCategory.text = categoryList[position].name
    }

    override fun getItemCount() = categoryList.size

    inner class CategoryViewHolder(binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imgCategory = binding.imgCategory
        val txtCategory = binding.txtCategory
        val bgCategory = binding.bgCategory
    }
}