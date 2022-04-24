package com.hiimgary.techwiser.di

import com.hiimgary.techwiser.network.TechyNetworkEntity
import com.hiimgary.techwiser.network.TechyNetworkMapper
import com.hiimgary.techwiser.network.TechyService
import com.hiimgary.techwiser.persistence.TechyCacheEntity
import com.hiimgary.techwiser.persistence.TechyCacheMapper
import com.hiimgary.techwiser.persistence.TechyDao
import com.hiimgary.techwiser.repository.FavoritesRepository
import com.hiimgary.techwiser.repository.MainRepository
import com.hiimgary.techwiser.repository.NewRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideMainRepository(
        techyDao: TechyDao,
        techyService: TechyService,
        techyCacheEntity: TechyCacheEntity,
        techyNetworkEntity: TechyNetworkEntity,
        techyCacheMapper: TechyCacheMapper,
        techyNetworkMapper: TechyNetworkMapper
    ): MainRepository {
        return MainRepository(
            techyDao,
            techyService,
            techyCacheEntity,
            techyNetworkEntity,
            techyCacheMapper,
            techyNetworkMapper
        )
    }

    @Singleton
    @Provides
    fun provideFavoritesRepository(
        techyDao: TechyDao,
        techyCacheEntity: TechyCacheEntity,
        techyCacheMapper: TechyCacheMapper,
    ): FavoritesRepository {
        return FavoritesRepository(
            techyDao,
            techyCacheEntity,
            techyCacheMapper,
        )
    }

    @Singleton
    @Provides
    fun provideNewRepository(
        techyDao: TechyDao,
        techyCacheEntity: TechyCacheEntity,
        techyCacheMapper: TechyCacheMapper,
    ): NewRepository {
        return NewRepository(
            techyDao,
            techyCacheEntity,
            techyCacheMapper,
        )
    }
}