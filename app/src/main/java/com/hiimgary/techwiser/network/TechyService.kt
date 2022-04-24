package com.hiimgary.techwiser.network

import retrofit2.http.GET

interface TechyService {
    @GET("json")
    suspend fun getRandomTechyQuote(): TechyNetworkEntity
}