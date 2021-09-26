package com.servian.sampletest.ui.viewmodels

import androidx.lifecycle.*
import com.servian.sampletest.model.Album
import com.servian.sampletest.repository.Repository
import com.servian.sampletest.utils.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AlbumViewModel(var repository: Repository) :ViewModel() {

    private val _response: MutableLiveData<Resource<List<Album>>> = MutableLiveData()
    val response: LiveData<Resource<List<Album>>> = _response

    fun albumList() = viewModelScope.launch {
        repository.fetchAlbums().collect { values ->
            _response.value = values
        }
    }

    fun filerAlbumListAccordingToID(userId: Int, list : List<Album>): List<Album>{
        return list.filter { album -> album.albumId == userId}
    }
}
