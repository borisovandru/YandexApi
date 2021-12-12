package com.android.wordtranslator.view.main.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.android.wordtranslator.databinding.MainItemBinding
import com.android.wordtranslator.databinding.RecyclerViewItemBinding
import com.android.wordtranslator.domain.model.DictionaryEntry
import com.android.wordtranslator.domain.storage.entity.WordTranslate
import com.android.wordtranslator.extensions.click

class WordViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {
    private val binding: MainItemBinding by viewBinding()
    fun bind(data: WordTranslate, delegate: WordAdapter.Delegate?) {
        with(binding) {
            header.text = data.word
            description.text = data.translate
            binding.root.click { delegate?.onItemPicked(data) }
            binding.favourite.click { delegate?.onFavouritePicked(data) }
        }
    }
}