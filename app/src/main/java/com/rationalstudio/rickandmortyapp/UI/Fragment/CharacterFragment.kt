package com.rationalstudio.rickandmortyapp.UI.Fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.SearchEvent
import android.view.View
import android.widget.Adapter
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.rationalstudio.rickandmortyapp.Adapters.CharacterAdapter
import com.rationalstudio.rickandmortyapp.Adapters.CharacterLoadStateAdapter
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
            recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = CharacterLoadStateAdapter{adapter.retry()},
                footer = CharacterLoadStateAdapter{adapter.retry()}

            )
        }

        viewModel.characters.observe(viewLifecycleOwner){
            adapter.submitData(viewLifecycleOwner.lifecycle,it)
        }

        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_search,menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query!=null){
                    binding.recyclerView.scrollToPosition(0)
                    viewModel.searchCharacters(query)
                    searchView.clearFocus()
                }
                return true


            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
    }

}