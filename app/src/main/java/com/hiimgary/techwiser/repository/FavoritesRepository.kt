package com.hiimgary.techwiser.repository

import com.hiimgary.techwiser.model.Techy
import com.hiimgary.techwiser.persistence.TechyCacheEntity
import com.hiimgary.techwiser.persistence.TechyCacheMapper
import com.hiimgary.techwiser.persistence.TechyDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FavoritesRepository constructor(
    private val techyDao: TechyDao,
    private val techyCacheMapper: TechyCacheMapper,
) {
    suspend fun getFavorites(): Flow<List<Techy>> = flow {
        val techies = techyCacheMapper.entityListToModelList(techyDao.getFavorites())
        emit(techies)
    }
}