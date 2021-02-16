package com.rationalstudio.rickandmortyapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.DrawableTransformation
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.rationalstudio.rickandmortyapp.Models.RickAndMortCharacterModel
import com.rationalstudio.rickandmortyapp.R
import com.rationalstudio.rickandmortyapp.databinding.RecyclerviewItemBinding

class CharacterAdapter: PagingDataAdapter<RickAndMortCharacterModel,CharacterAdapter.CharacterViewHolder>(
    COMPARATOR) {
    inner class CharacterViewHolder(private val binding: RecyclerviewItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(character :RickAndMortCharacterModel){
            with(binding){
            Glide.with(itemView)
                .load(character.image)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.ic_error)
                .into(image)

                tvName.text = character.name
                tvStatus.text = character.status
            }
        }
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val currentItem: RickAndMortCharacterModel?= getItem(position)
        if (currentItem!=null){
            holder.bind(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding: RecyclerviewItemBinding = RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CharacterViewHolder(binding)
    }
    companion object{
        private val COMPARATOR = object : DiffUtil.ItemCallback<RickAndMortCharacterModel>(){
            override fun areItemsTheSame(
                oldItem: RickAndMortCharacterModel,
                newItem: RickAndMortCharacterModel
            ): Boolean {
                return oldItem.image == newItem.image
            }

            override fun areContentsTheSame(
                oldItem: RickAndMortCharacterModel,
                newItem: RickAndMortCharacterModel
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

}