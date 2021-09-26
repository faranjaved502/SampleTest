package com.servian.sampletest.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.servian.sampletest.repository.Repository
import javax.inject.Inject

class AlbumViewModelFactory @Inject constructor(
    var repository: Repository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AlbumViewModel(repository) as T
    }
}