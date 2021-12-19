package com.android.screenhistory.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.android.domain.storage.entity.WordTranslate
import com.android.screenhistory.databinding.HistoryItemBinding
import com.android.utils.extensions.click

class HistoryWordViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {
    private val binding: HistoryItemBinding by viewBinding()
    fun bind(
        data: WordTranslate,
        delegate: HistoryWordAdapter.Delegate?
    ) {
        with(binding) {
            header.text = data.word
            description.text = data.translate
            binding.root.click { delegate?.onItemPicked(data) }
        }
    }
}