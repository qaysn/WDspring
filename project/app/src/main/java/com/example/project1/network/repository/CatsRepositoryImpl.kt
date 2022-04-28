package com.example.project1.network.repository

import com.example.project1.data.models.CatInfo
import com.example.project1.network.api.CatsApi

class CatsRepositoryImpl(private val catsApi: CatsApi) : CatsRepository {

    override suspend fun getRandomCatInfo(): List<CatInfo>? {
        return catsApi.getRandomCat().await().body()
    }
}