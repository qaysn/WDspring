package com.example.project1.network.api

import com.example.project1.data.models.CatInfo
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface CatsApi {

    @GET("images/search")
    fun getRandomCat(): Deferred<Response<List<CatInfo>>>

}