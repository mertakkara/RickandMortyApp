package com.rationalstudio.rickandmortyapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rationalstudio.rickandmortyapp.databinding.LoadStateFooterBinding
import com.rationalstudio.rickandmortyapp.databinding.RecyclerviewItemBinding

class EpisodeLoadStateAdapter(private val retry:()->Unit):LoadStateAdapter<EpisodeLoadStateAdapter.LoadStateViewHolder>() {
    inner class LoadStateViewHolder(private val binding: LoadStateFooterBinding): RecyclerView.ViewHolder(binding.root){

        init {
            binding.btnRetry.setOnClickListener{
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState){
            with(binding){
                progressBar.isVisible = loadState is LoadState.Loading
                btnRetry.isVisible = loadState !is LoadState.Loading
                tvError.isVisible = loadState !is LoadState.Loading

            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding: LoadStateFooterBinding = LoadStateFooterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LoadStateViewHolder(binding)

    }
    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
holder.bind(loadState)
    }



}