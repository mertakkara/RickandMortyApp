package com.rationalstudio.rickandmortyapp

import androidx.paging.PagingSource
import com.rationalstudio.rickandmortyapp.Api.RetroService
import com.rationalstudio.rickandmortyapp.Models.EpisodeModel
import retrofit2.HttpException
import java.io.IOException


private const val STARTING_PAGE_INDEX = 1
class EpisodePagingSource(
        private val retroService: RetroService,
        private val name: String?
): PagingSource<Int, EpisodeModel>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EpisodeModel> {
        return try {
            val position = params.key?: STARTING_PAGE_INDEX
            val response = if(name!=null) retroService.searchEpisode(name,position) else retroService.getEpisode(position)
            val episodes = response.results

            LoadResult.Page(
                    data = episodes,
                    prevKey = if(position == STARTING_PAGE_INDEX) null else position-1,
                    nextKey = if (episodes.isEmpty()) null else position+1
            )
        } catch (e: IOException){
            LoadResult.Error(e)
        } catch (e: HttpException){
            LoadResult.Error(e)
        }

    }

}