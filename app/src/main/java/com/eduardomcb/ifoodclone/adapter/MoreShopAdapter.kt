package com.eduardomcb.ifoodclone.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eduardomcb.ifoodclone.R
import com.eduardomcb.ifoodclone.databinding.MoreShopItemBinding
import com.eduardomcb.ifoodclone.model.MoreShop
import com.squareup.picasso.Picasso

class MoreShopAdapter(
    private val context: Context,
    private val moreShopList: MutableList<MoreShop>
) :
    RecyclerView.Adapter<MoreShopAdapter.MoreShopViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoreShopViewHolder {
        val itemList = MoreShopItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return MoreShopViewHolder(itemList)
    }

    override fun onBindViewHolder(
        holder: MoreShopViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        Picasso.get().load(moreShopList[position].logoUrl).into(holder.imgShop)

        holder.txtShop.text = moreShopList[position].text
        holder.txtStar.text = moreShopList[position].rate.toString()
        holder.txtSubtitle.text = context.getString(R.string.shop_category, moreShopList[position].category, moreShopList[position].distance)
        holder.txtPrice.text = context.getString(R.string.shop_price, moreShopList[position].time, moreShopList[position].price)
    }

    override fun getItemCount() = moreShopList.size

    inner class MoreShopViewHolder(binding: MoreShopItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imgShop = binding.imgShop
        val txtShop = binding.txtShop
        val txtStar = binding.txtStar
        val txtSubtitle = binding.txtSubtitle
        val txtPrice = binding.txtPrice
    }
}