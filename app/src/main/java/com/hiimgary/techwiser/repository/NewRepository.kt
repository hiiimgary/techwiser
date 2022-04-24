package com.hiimgary.techwiser.repository

import com.hiimgary.techwiser.persistence.TechyCacheEntity
import com.hiimgary.techwiser.persistence.TechyCacheMapper
import com.hiimgary.techwiser.persistence.TechyDao

class NewRepository constructor(
    private val techyDao: TechyDao,
    private val techyCacheEntity: TechyCacheEntity,
    private val techyCacheMapper: TechyCacheMapper,
) {
}