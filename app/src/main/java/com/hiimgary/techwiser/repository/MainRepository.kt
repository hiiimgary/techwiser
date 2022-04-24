package com.hiimgary.techwiser.repository

import com.hiimgary.techwiser.network.TechyNetworkEntity
import com.hiimgary.techwiser.network.TechyNetworkMapper
import com.hiimgary.techwiser.network.TechyService
import com.hiimgary.techwiser.persistence.TechyCacheEntity
import com.hiimgary.techwiser.persistence.TechyCacheMapper
import com.hiimgary.techwiser.persistence.TechyDao

class MainRepository constructor(
    private val techyDao: TechyDao,
    private val techyService: TechyService,
    private val techyCacheEntity: TechyCacheEntity,
    private val techyNetworkEntity: TechyNetworkEntity,
    private val techyCacheMapper: TechyCacheMapper,
    private val techyNetworkMapper: TechyNetworkMapper
) {
}