package com.servian.sampletest.repository

import com.servian.sampletest.model.Album
import com.servian.sampletest.model.BaseApiResponse
import com.servian.sampletest.model.User
import com.servian.sampletest.network.RetrofitAPI
import com.servian.sampletest.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: RetrofitAPI
):BaseApiResponse() {

    suspend fun fetchUsers() : Flow<Resource<List<User>>> {
        return flow {
            emit(safeApiCall { api.fetchAllUsers() })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun fetchAlbums() : Flow<Resource<List<Album>>> {
        return flow {
            emit(safeApiCall { api.fetchAlbum() })
        }.flowOn(Dispatchers.IO)
    }

}