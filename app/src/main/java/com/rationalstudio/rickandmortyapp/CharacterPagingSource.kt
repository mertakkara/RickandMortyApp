package com.rationalstudio.rickandmortyapp

import androidx.paging.PagingSource
import com.rationalstudio.rickandmortyapp.Api.RetroInstance
import com.rationalstudio.rickandmortyapp.Api.RetroService
import com.rationalstudio.rickandmortyapp.Models.RickAndMortCharacterModel
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class CharacterPagingSource (
    private val retroService: RetroService
        ): PagingSource<Int,RickAndMortCharacterModel>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RickAndMortCharacterModel> {
        return try {
            val position = params.key?: STARTING_PAGE_INDEX
            val response = retroService.getData(position)
            val characters = response.results

            LoadResult.Page(
                data = characters,
                prevKey = if(position == STARTING_PAGE_INDEX) null else position-1,
                nextKey = if (characters.isEmpty()) null else position+1
            )
        } catch (e: IOException){
            LoadResult.Error(e)
        } catch (e: HttpException){
            LoadResult.Error(e)
        }

    }

}