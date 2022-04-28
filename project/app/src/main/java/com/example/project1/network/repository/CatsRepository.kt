package com.example.project1.network.repository

import com.example.project1.data.models.CatInfo

interface CatsRepository {

    suspend fun getRandomCatInfo(): List<CatInfo>?

}