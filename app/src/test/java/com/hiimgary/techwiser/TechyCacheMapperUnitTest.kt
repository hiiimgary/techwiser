package com.hiimgary.techwiser

import com.hiimgary.techwiser.model.Techy
import com.hiimgary.techwiser.persistence.TechyCacheEntity
import com.hiimgary.techwiser.persistence.TechyCacheMapper
import org.junit.Test

class TechyCacheMapperUnitTest {
    private var techyCacheMapper = TechyCacheMapper()

    @Test
    fun mapEntityToModel() {
        val techy = TechyCacheEntity(1, "Test Techy quote")
        assert(techyCacheMapper.entityToModel(techy) == Techy("Test Techy quote"))
    }

    @Test
    fun mapModelToEntity() {
        val techy = Techy("Test Techy quote")
        assert(techyCacheMapper.modelToEntity(techy).quote === "Test Techy quote" )
    }
}