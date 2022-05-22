package com.hiimgary.techwiser

import com.hiimgary.techwiser.model.Techy
import com.hiimgary.techwiser.network.TechyNetworkEntity
import com.hiimgary.techwiser.network.TechyNetworkMapper
import com.hiimgary.techwiser.persistence.TechyCacheEntity
import com.hiimgary.techwiser.persistence.TechyCacheMapper
import org.junit.Test

class TechyNetworkMapperUnitTest {
    private var techyNetworkMapper = TechyNetworkMapper()

    @Test
    fun mapEntityToModel() {
        val techy = TechyNetworkEntity("Test Techy quote")
        assert(techyNetworkMapper.entityToModel(techy) == Techy("Test Techy quote"))
    }
}