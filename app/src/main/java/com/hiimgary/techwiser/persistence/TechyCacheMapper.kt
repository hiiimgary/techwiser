package com.hiimgary.techwiser.persistence

import com.hiimgary.techwiser.model.Techy

class TechyCacheMapper {
    fun entityToModel(entity: TechyCacheEntity): Techy {
        return Techy(
            quote = entity.quote
        )
    }

    fun modelToEntity(domainModel: Techy): TechyCacheEntity {
        return TechyCacheEntity(
            quote = domainModel.quote
        )
    }

    fun entityListToModelList(entities: List<TechyCacheEntity>): List<Techy> {
        return entities.map { entityToModel(it) }
    }
}