package com.github.aleksandrgrebenkin.historyscreen.view.history.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.aleksandrgrebenkin.historyscreen.databinding.ItemHistoryBinding
import com.github.aleksandrgrebenkin.model.entity.Word

class HistoryAdapter(
    private var data: List<Word>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    fun setData(words: List<Word>) {
        data = words
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], onItemClickListener)
    }

    override fun getItemCount() = data.size

    inner class ViewHolder(private val itemBinding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(word: Word, onItemClickListener: OnItemClickListener) {
            itemBinding.historyText.text = word.text
            itemBinding.historyMeanings.text =
                word.meanings[0].let { "${it.translation} (${it.partOfSpeechCode})" }

            itemView.setOnClickListener { onItemClickListener.onItemClicked(word) }
        }

    }

    interface OnItemClickListener {
        fun onItemClicked(word: Word)
    }
}