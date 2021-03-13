package com.github.aleksandrgrebenkin.searchscreen.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.aleksandrgrebenkin.model.entity.Word
import com.github.aleksandrgrebenkin.searchscreen.databinding.ItemWordBinding

class SearchAdapter(
    private var data: List<Word>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    fun setData(words: List<Word>) {
        data = words
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemWordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], onItemClickListener)
    }

    override fun getItemCount() = data.size

    inner class ViewHolder(private val itemBinding: ItemWordBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(word: Word, onItemClickListener: OnItemClickListener) {
            itemBinding.text.text = word.text
            itemBinding.meanings.text =
                word.meanings[0].let { "${it.translation} (${it.partOfSpeechCode})" }

            itemView.setOnClickListener { onItemClickListener.onItemClicked(word) }
        }

    }

    interface OnItemClickListener {
        fun onItemClicked(word: Word)
    }
}