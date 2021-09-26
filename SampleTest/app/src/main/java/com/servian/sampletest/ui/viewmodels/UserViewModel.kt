package com.servian.sampletest.ui.viewmodels


import androidx.lifecycle.*
import com.servian.sampletest.model.User
import com.servian.sampletest.repository.Repository
import com.servian.sampletest.utils.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserViewModel(var repository: Repository) : ViewModel() {

    private val _response: MutableLiveData<Resource<List<User>>> = MutableLiveData()
    val response: LiveData<Resource<List<User>>> = _response

    fun userList() = viewModelScope.launch {
        repository.fetchUsers().collect { values ->
            _response.value = values

        }
    }


    /*val userList = liveData<Result<List<User>>> {
        loader.postValue(true)
        emitSource(repository.fetchUsers()
            .onEach {
                loader.postValue(false)
            }
            .asLiveData())
    }*/
}