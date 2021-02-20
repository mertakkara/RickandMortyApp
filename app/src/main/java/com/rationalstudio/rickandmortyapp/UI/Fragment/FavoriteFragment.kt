package com.rationalstudio.rickandmortyapp.UI.Fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.rationalstudio.rickandmortyapp.Adapters.FavoriteAdapter
import com.rationalstudio.rickandmortyapp.Models.FavoriteCharacter
import com.rationalstudio.rickandmortyapp.Models.RickAndMortyCharacterModel
import com.rationalstudio.rickandmortyapp.R
import com.rationalstudio.rickandmortyapp.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_favorite.*


@AndroidEntryPoint
class FavoriteFragment: Fragment(R.layout.fragment_favorite)  {

    private val viewModel by viewModels<FavoriteViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentFavoriteBinding.bind(view)

        val adapter = FavoriteAdapter()

        viewModel.characters.observe(viewLifecycleOwner){
            adapter.setCharacterList(it)
            binding.apply {
                rv_character.setHasFixedSize(true)
                rv_character.adapter = adapter
            }
        }


        adapter.setOnItemClickCallback(object : FavoriteAdapter.OnItemClickCallback{
            override fun onItemClick(favoriteCharacter: FavoriteCharacter) {
                val character = RickAndMortyCharacterModel(
                        favoriteCharacter.cid,
                        favoriteCharacter.name,
                        favoriteCharacter.cimage,
                        favoriteCharacter.status
                )
               // val action = FavoriteFragmentDirections.actionNavFavoriteToNavDetails(character)
              //  findNavController().navigate(action)
            }

        })
    }

}