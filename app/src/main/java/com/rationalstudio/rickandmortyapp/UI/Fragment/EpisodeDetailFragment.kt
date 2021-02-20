package com.rationalstudio.rickandmortyapp.UI.Fragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.rationalstudio.rickandmortyapp.R
import com.rationalstudio.rickandmortyapp.databinding.EpisodeDetailFragmentBinding
import com.rationalstudio.rickandmortyapp.databinding.FragmentDetailsBinding

class EpisodeDetailFragment:Fragment(R.layout.episode_detail_fragment) {
    private val args by navArgs<EpisodeDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = EpisodeDetailFragmentBinding.bind(view)

        binding.apply {
            tvEpisode.isVisible = true
            tvName.isVisible = true
            val episode = args.episode
            tvEpisode.text = episode.episode
            tvName.text = episode.name


        }
    }
}