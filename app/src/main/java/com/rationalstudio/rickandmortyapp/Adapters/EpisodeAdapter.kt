package com.rationalstudio.rickandmortyapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.rationalstudio.rickandmortyapp.Models.EpisodeModel
import com.rationalstudio.rickandmortyapp.Models.RickAndMortyCharacterModel
import com.rationalstudio.rickandmortyapp.R
import com.rationalstudio.rickandmortyapp.databinding.ItemEpisodeBinding
import com.rationalstudio.rickandmortyapp.databinding.RecyclerviewItemBinding

class EpisodeAdapter(private val listener:OnItemClickListener): PagingDataAdapter<EpisodeModel,EpisodeAdapter.EpisodeViewHolder>(
    COMPARATOR) {
    inner class EpisodeViewHolder(private val binding: ItemEpisodeBinding):RecyclerView.ViewHolder(binding.root){
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

        fun bind(rickAndMortyEpisode :EpisodeModel){
            with(binding){

                tvName.text = rickAndMortyEpisode.name
                tvEpisode.text = rickAndMortyEpisode.episode
            }
        }
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val currentItem: EpisodeModel?= getItem(position)
        if (currentItem!=null){
            holder.bind(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val binding: ItemEpisodeBinding = ItemEpisodeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return EpisodeViewHolder(binding)
    }
    companion object{
        private val COMPARATOR = object : DiffUtil.ItemCallback<EpisodeModel>(){
            override fun areItemsTheSame(
                    oldItem: EpisodeModel,
                    newItem: EpisodeModel
            ): Boolean {
                return oldItem.kid == newItem.kid
            }

            override fun areContentsTheSame(
                    oldItem: EpisodeModel,
                    newItem: EpisodeModel
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    interface OnItemClickListener{
        fun onItemClick(episodeModel: EpisodeModel)
    }

}