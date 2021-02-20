package com.rationalstudio.rickandmortyapp.UI.Fragment

import android.app.DownloadManager
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.rationalstudio.rickandmortyapp.Repository.CharacterRepository
import com.rationalstudio.rickandmortyapp.Repository.EpisodeRepository

class EpisodeViewModel @ViewModelInject constructor(private val repository: EpisodeRepository,
                                                    @Assisted state: SavedStateHandle
):ViewModel() {

    companion object{
        private const val CURRENT_QUERY="current_query"
        private const val EMPTY_QUERY=""
    }
    private val currentQuery=state.getLiveData(CURRENT_QUERY, EMPTY_QUERY)
    val episodes = currentQuery.switchMap {query->
        if( !query.isEmpty()){
            repository.getSearchEpisode(query)
        }else
            repository.getAllEpisodes().cachedIn(viewModelScope)

    }

    fun searchEpisodes(query: String){
        currentQuery.value = query
    }
}