package com.hiimgary.techwiser.repository

import com.hiimgary.techwiser.model.Techy
import com.hiimgary.techwiser.persistence.TechyCacheMapper
import com.hiimgary.techwiser.persistence.TechyDao

class NewRepository constructor(
    private val techyDao: TechyDao,
    private val techyCacheMapper: TechyCacheMapper,
) {

    suspend fun addToFavorite(techy: Techy) {
        techyDao.addFavorite(techyCacheMapper.modelToEntity(techy))
    }
}