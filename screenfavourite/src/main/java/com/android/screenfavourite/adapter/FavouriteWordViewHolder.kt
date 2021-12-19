package com.android.screenfavourite.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.android.domain.storage.entity.WordFavourite
import com.android.screenfavourite.databinding.FavouriteItemBinding
import com.android.utils.extensions.click

class FavouriteWordViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {
    private val binding: FavouriteItemBinding by viewBinding()
    fun bind(data: WordFavourite, delegate: FavouriteWordAdapter.Delegate?) {
        with(binding) {
            header.text = data.word
            description.text = data.translate
            binding.root.click { delegate?.onItemPicked(data) }
        }
    }
}