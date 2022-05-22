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

    suspend fun deleteFavorite(techy: Techy) {
        techyDao.deleteFavorite(techy.quote)
    }

    suspend fun addToFavorite(techy: Techy) {
        techyDao.addFavorite(techyCacheMapper.modelToEntity(techy))
    }

    suspend fun updateFavorite(oldQuote: String, newQuote: String) {
        techyDao.updateFavorite(oldQuote, newQuote)
    }
}