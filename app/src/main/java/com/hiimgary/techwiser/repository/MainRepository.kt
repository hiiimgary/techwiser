package com.hiimgary.techwiser.repository

import com.hiimgary.techwiser.model.Techy
import com.hiimgary.techwiser.network.TechyNetworkMapper
import com.hiimgary.techwiser.network.TechyService
import com.hiimgary.techwiser.persistence.TechyCacheMapper
import com.hiimgary.techwiser.persistence.TechyDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepository constructor(
    private val techyDao: TechyDao,
    private val techyService: TechyService,
    private val techyCacheMapper: TechyCacheMapper,
    private val techyNetworkMapper: TechyNetworkMapper
) {

    suspend fun getTechyQuote(): Flow<Techy> = flow {
        val techy = techyNetworkMapper.entityToModel(techyService.getRandomTechyQuote())
        emit(techy)
    }

    suspend fun addToFavorite(techy: Techy) {
        techyDao.addFavorite(techyCacheMapper.modelToEntity(techy))
    }
}