package com.rationalstudio.rickandmortyapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.rationalstudio.rickandmortyapp.Models.RickAndMortyCharacterModel
import com.rationalstudio.rickandmortyapp.R
import com.rationalstudio.rickandmortyapp.databinding.RecyclerviewItemBinding

class CharacterAdapter(private val listener:OnItemClickListener): PagingDataAdapter<RickAndMortyCharacterModel,CharacterAdapter.CharacterViewHolder>(
    COMPARATOR) {
    inner class CharacterViewHolder(private val binding: RecyclerviewItemBinding):RecyclerView.ViewHolder(binding.root){
        init {
            binding.root.setOnClickListener{
                val position = bindingAdapterPosition
                if(position != RecyclerView.NO_POSITION){
                    val item = getItem(position)
                    if(item != null){
                        listener.onItemClick(item)
                    }

                }
            }
        }

        fun bind(rickAndMortyCharacter :RickAndMortyCharacterModel){
            with(binding){
            Glide.with(itemView)
                .load(rickAndMortyCharacter.image)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.ic_error)
                .into(image)

                tvName.text = rickAndMortyCharacter.name
                tvStatus.text = rickAndMortyCharacter.status
            }
        }
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val currentItem: RickAndMortyCharacterModel?= getItem(position)
        if (currentItem!=null){
            holder.bind(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding: RecyclerviewItemBinding = RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CharacterViewHolder(binding)
    }
    companion object{
        private val COMPARATOR = object : DiffUtil.ItemCallback<RickAndMortyCharacterModel>(){
            override fun areItemsTheSame(
                    oldItem: RickAndMortyCharacterModel,
                    newItem: RickAndMortyCharacterModel
            ): Boolean {
                return oldItem.image == newItem.image
            }

            override fun areContentsTheSame(
                    oldItem: RickAndMortyCharacterModel,
                    newItem: RickAndMortyCharacterModel
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    interface OnItemClickListener{
        fun onItemClick(rickAndMortyCharacter: RickAndMortyCharacterModel)
    }

}