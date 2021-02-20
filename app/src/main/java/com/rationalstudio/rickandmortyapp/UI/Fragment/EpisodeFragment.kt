package com.rationalstudio.rickandmortyapp.UI.Fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.rationalstudio.rickandmortyapp.Adapters.CharacterAdapter
import com.rationalstudio.rickandmortyapp.Adapters.CharacterLoadStateAdapter
import com.rationalstudio.rickandmortyapp.Adapters.EpisodeAdapter
import com.rationalstudio.rickandmortyapp.Adapters.EpisodeLoadStateAdapter
import com.rationalstudio.rickandmortyapp.Models.EpisodeModel
import com.rationalstudio.rickandmortyapp.Models.RickAndMortyCharacterModel
import com.rationalstudio.rickandmortyapp.R
import com.rationalstudio.rickandmortyapp.databinding.FragmentCharacterBinding
import com.rationalstudio.rickandmortyapp.databinding.FragmentEpisodeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeFragment: Fragment(R.layout.fragment_episode), EpisodeAdapter.OnItemClickListener {
    private val viewModel by viewModels<EpisodeViewModel>()
    private var _binding : FragmentEpisodeBinding? = null
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEpisodeBinding.bind(view)

        val adapter = EpisodeAdapter(this)

        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                    header = EpisodeLoadStateAdapter{adapter.retry()},
                    footer = EpisodeLoadStateAdapter{adapter.retry()}

            )
            btnTryAgain.setOnClickListener{
                adapter.retry()
            }

        }


        adapter.addLoadStateListener {loadState->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                btnTryAgain.isVisible = loadState.source.refresh is LoadState.Error
                tvFailed.isVisible = loadState.source.refresh is LoadState.Error

                if(loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached && adapter.itemCount<1){
                    recyclerView.isVisible = false
                    tvNotFound.isVisible = true
                }else{
                    tvNotFound.isVisible = false
                }

            }

        }

        viewModel.episodes.observe(viewLifecycleOwner){
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
                    viewModel.searchEpisodes(query)
                    searchView.clearFocus()
                }
                return true


            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })

    }


    override fun onItemClick(episodeModel: EpisodeModel) {
        val action = EpisodeFragmentDirections.actionNavEpisodeToEpisodeDetailFragment(episodeModel)
        findNavController().navigate(action)


    }


}