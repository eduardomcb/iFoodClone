package com.eduardomcb.ifoodclone.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eduardomcb.ifoodclone.databinding.ShopItemBinding
import com.eduardomcb.ifoodclone.model.Shop
import com.squareup.picasso.Picasso

class ShopAdapter(
    private val context: Context,
    private val shopList: MutableList<Shop>
) :
    RecyclerView.Adapter<ShopAdapter.ShopViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        val itemList = ShopItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ShopViewHolder(itemList)
    }

    override fun onBindViewHolder(
        holder: ShopViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        Picasso.get().load(shopList[position].logoUrl).into(holder.imgShop)

        holder.txtShop.text = shopList[position].text
    }

    override fun getItemCount() = shopList.size

    inner class ShopViewHolder(binding: ShopItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imgShop = binding.imgShop
        val txtShop = binding.txtShop
    }
}