package com.github.aleksandrgrebenkin.meaningscreen.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.aleksandrgrebenkin.meaningscreen.R
import com.github.aleksandrgrebenkin.meaningscreen.databinding.ItemMeaningBinding
import com.github.aleksandrgrebenkin.model.entity.Meaning

class MeaningAdapter(private var data: List<Meaning>) :
    RecyclerView.Adapter<MeaningAdapter.ViewHolder>() {
    fun setData(meanings: List<Meaning>) {
        data = meanings
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemMeaningBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    inner class ViewHolder(private val itemBinding: ItemMeaningBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(meaning: Meaning) {
            itemBinding.let {
                it.meaningTranslation.text = meaning.translation
                it.meaningPartOfSpeech.text = meaning.partOfSpeechCode

                Glide.with(it.root)
                    .load(meaning.imageUrl)
                    .placeholder(R.drawable.image_placeholder)
                    .error(R.drawable.image_placeholder)
                    .circleCrop()
                    .into(it.meaningImage)
            }


        }

    }
}