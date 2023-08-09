package com.eduardomcb.ifoodclone.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eduardomcb.ifoodclone.databinding.BannerItemBinding
import com.eduardomcb.ifoodclone.model.Banner
import com.squareup.picasso.Picasso

class BannerAdapter(
    private val context: Context,
    private val bannerList: MutableList<Banner>
) :
    RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val itemList = BannerItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return BannerViewHolder(itemList)
    }

    override fun getItemCount() = bannerList.size

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        Picasso.get().load(bannerList[position].bannerUrl).into(holder.imgBanner)
    }

    inner class BannerViewHolder(binding: BannerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imgBanner = binding.imgBanner

    }

}