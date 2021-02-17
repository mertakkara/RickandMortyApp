package com.rationalstudio.rickandmortyapp.UI.Fragment

import android.app.DownloadManager
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.rationalstudio.rickandmortyapp.Repository.CharacterRepository

class CharacterViewModel @ViewModelInject constructor(private val repository: CharacterRepository,
@Assisted state: SavedStateHandle
):ViewModel() {

    companion object{
        private const val CURRENT_QUERY="current_query"
        private const val EMPTY_QUERY=""
    }
    private val currentQuery=state.getLiveData(CURRENT_QUERY, EMPTY_QUERY)
    val characters = currentQuery.switchMap {query->
        if( !query.isEmpty()){
            repository.getSearchCharacters(query)
        }else
            repository.getAllCharacters()

    }

    fun searchCharacters(query: String){
        currentQuery.value = query
    }
}