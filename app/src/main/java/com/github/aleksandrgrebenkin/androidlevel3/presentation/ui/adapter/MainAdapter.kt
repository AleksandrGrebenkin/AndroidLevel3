package com.github.aleksandrgrebenkin.androidlevel3.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.aleksandrgrebenkin.androidlevel3.databinding.ItemWordBinding
import com.github.aleksandrgrebenkin.androidlevel3.domain.entity.Word

class MainAdapter(private var data: List<Word>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
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
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    inner class ViewHolder(private val itemBinding: ItemWordBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(word: Word) {
            itemBinding.text.text = word.text
            itemBinding.meanings.text =
                "${word.meanings[0].translation} (${word.meanings[0].partOfSpeechCode})"
        }

    }
}