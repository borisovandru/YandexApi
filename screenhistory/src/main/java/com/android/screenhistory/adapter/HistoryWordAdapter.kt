package com.android.screenhistory.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.screenhistory.R
import com.android.domain.storage.entity.WordTranslate

class HistoryWordAdapter(private val delegate: Delegate?) :
    RecyclerView.Adapter<HistoryWordViewHolder>() {
    interface Delegate {
        /**
         * Событие наступает при выборе
         * строки перевода.
         * @param word Слово
         */
        fun onItemPicked(word: WordTranslate)
    }

    private val data = ArrayList<WordTranslate>()
    fun setData(newList: ArrayList<WordTranslate>) {
        val result = DiffUtil.calculateDiff(DiffUtilCallback(this.data, newList))
        result.dispatchUpdatesTo(this)
        this.data.clear()
        this.data.addAll(newList)
    }

    fun clear() {
        this.data.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryWordViewHolder {
        return HistoryWordViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.history_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: HistoryWordViewHolder, position: Int) {
        holder.bind(data[position], delegate)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class DiffUtilCallback(
        private var oldItems: ArrayList<WordTranslate>,
        private var newItems: ArrayList<WordTranslate>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldItems.size
        override fun getNewListSize(): Int = newItems.size
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition].partOfSpeech == newItems[newItemPosition].partOfSpeech
                    && oldItems[oldItemPosition].word == newItems[newItemPosition].word

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition] == newItems[newItemPosition]
    }
}