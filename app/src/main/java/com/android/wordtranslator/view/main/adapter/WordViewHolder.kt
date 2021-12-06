package com.android.wordtranslator.view.main.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.android.wordtranslator.databinding.RecyclerViewItemBinding
import com.android.wordtranslator.domain.model.DictionaryEntry
import com.android.wordtranslator.extensions.click

class WordViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {
    private val binding: RecyclerViewItemBinding by viewBinding()
    fun bind(data: DictionaryEntry, delegate: WordAdapter.Delegate?) {
        with(binding) {
            "${data.partOfSpeech} - [${data.transcription}]".also { extensionInfo.text = it }
            headerTextviewRecyclerItem.text = data.text
            descriptionTextviewRecyclerItem.text =
                data.translatesList.joinToString(separator = "\n")
            binding.root.click { delegate?.onItemPicked(data) }
        }
    }
}