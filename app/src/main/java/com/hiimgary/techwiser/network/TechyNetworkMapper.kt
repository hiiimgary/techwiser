package com.hiimgary.techwiser.network

import com.hiimgary.techwiser.model.Techy
import javax.inject.Inject

class TechyNetworkMapper {
    fun entityToModel(entity: TechyNetworkEntity): Techy {
        return Techy(quote = entity.quote);
    }
}