package com.servian.sampletest.network

import com.servian.sampletest.model.Album
import com.servian.sampletest.model.User
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitAPI {

    @GET("users")
    suspend fun fetchAllUsers() : Response<List<User>>


    @GET("photos")
    suspend fun fetchAlbum() : Response<List<Album>>
}