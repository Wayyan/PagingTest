package com.example.pagingtest.feature.main.paging

import androidx.recyclerview.widget.RecyclerView
import com.example.pagingtest.databinding.BeerItemBinding
import com.example.pagingtest.domain.beer.model.BeerModel
import com.example.pagingtest.extension.loadUrl

class BeerViewHolder(private val binding: BeerItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(model: BeerModel) {
        binding.image.loadUrl(model.imageUrl)
        binding.tvTitle.text = "${model.id} - ${model.name}"
        binding.tvDescription.text = model.description
        binding.tvTagline.text = model.tagline
    }
}