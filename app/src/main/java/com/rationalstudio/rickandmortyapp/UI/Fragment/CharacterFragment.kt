package com.rationalstudio.rickandmortyapp.UI.Fragment

import android.os.Bundle
import android.view.View
import android.widget.Adapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.rationalstudio.rickandmortyapp.Adapters.CharacterAdapter
import com.rationalstudio.rickandmortyapp.R
import com.rationalstudio.rickandmortyapp.databinding.FragmentCharacterBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CharacterFragment:Fragment(R.layout.fragment_character) {
    private val viewModel by viewModels<CharacterViewModel>()
    private var _binding : FragmentCharacterBinding? = null
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCharacterBinding.bind(view)

        val adapter = CharacterAdapter()

        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
        }

        viewModel.characters.observe(viewLifecycleOwner){
            adapter.submitData(viewLifecycleOwner.lifecycle,it)
        }

    }

}