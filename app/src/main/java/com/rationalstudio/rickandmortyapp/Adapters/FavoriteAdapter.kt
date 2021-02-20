package com.rationalstudio.rickandmortyapp.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.rationalstudio.rickandmortyapp.Models.FavoriteCharacter
import com.rationalstudio.rickandmortyapp.R
import com.rationalstudio.rickandmortyapp.databinding.RecyclerviewItemBinding
import kotlinx.android.synthetic.main.episode_detail_fragment.view.*

class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>()  {
    private lateinit var list : List<FavoriteCharacter>

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    fun setCharacterList(list: List<FavoriteCharacter>){
        this.list = list
        notifyDataSetChanged()
    }

    inner class FavoriteViewHolder(private val binding: RecyclerviewItemBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteCharacter: FavoriteCharacter) {
            with(binding) {

                Glide.with(itemView)
                        .load(favoriteCharacter.cimage)
                        .centerCrop()
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .error(R.drawable.ic_error)
                        .into(image)
                tvName.text = favoriteCharacter.name
                binding.root.setOnClickListener { onItemClickCallback?.onItemClick(favoriteCharacter) }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(list[position])
    }

    interface OnItemClickCallback {
        fun onItemClick(favoriteCharacter: FavoriteCharacter)
    }
}