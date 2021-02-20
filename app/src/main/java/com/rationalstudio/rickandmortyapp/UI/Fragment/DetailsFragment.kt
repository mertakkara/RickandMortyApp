package com.rationalstudio.rickandmortyapp.UI.Fragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.rationalstudio.rickandmortyapp.R
//import com.rationalstudio.rickandmortyapp.UI.DetailsViewModel
import com.rationalstudio.rickandmortyapp.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.recyclerview_item.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class DetailsFragment:Fragment(R.layout.fragment_details) {
    private val args by navArgs<DetailsFragmentArgs>()
    private val viewModel by viewModels<DetailsViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDetailsBinding.bind(view)

        binding.apply {
            val character = args.character
            Glide.with(this@DetailsFragment)
                    .load(character.image)
                    .error(R.drawable.ic_error)
                    .listener(object : RequestListener<Drawable>{
                        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                            progressBar.isVisible =  false
                            return false
                        }

                        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                            progressBar.isVisible =  false
                            tvDescription.isVisible = true
                            tvCharacterTitle.isVisible = true
                            return false

                        }

                    }).into(ivCharacterPoster)

            var _isChecked = false
            CoroutineScope(Dispatchers.IO).launch{
                val count = viewModel.checkCharacter(character.kid)
                withContext(Main){
                    if (count > 0){
                        toggleFavorite.isChecked = true
                        _isChecked = true
                    }else{
                        toggleFavorite.isChecked = false
                        _isChecked = false
                    }
                }
            }

            tvDescription.text = character.status
            tvCharacterTitle.text = character.name

            toggleFavorite.setOnClickListener {
                _isChecked = !_isChecked
                if (_isChecked){
                    viewModel.addToFavorite(character)
                } else{
                    viewModel.removeFromFavorite(character.kid)
                }
                toggleFavorite.isChecked = _isChecked
            }
        }

        }
    }


