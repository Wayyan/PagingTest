package com.example.pagingtest.feature.main.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.pagingtest.databinding.BeerItemBinding
import com.example.pagingtest.domain.beer.model.BeerModel

class BeerAdapter :
    PagingDataAdapter<BeerModel, BeerViewHolder>(
        MOVIE_DIFF_CALLBACK
    ) {

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val binding = BeerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BeerViewHolder(binding)
    }

    companion object {
        private val MOVIE_DIFF_CALLBACK = object : DiffUtil.ItemCallback<BeerModel>() {
            override fun areItemsTheSame(oldItem: BeerModel, newItem: BeerModel): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: BeerModel, newItem: BeerModel): Boolean =
                oldItem == newItem
        }
    }
}