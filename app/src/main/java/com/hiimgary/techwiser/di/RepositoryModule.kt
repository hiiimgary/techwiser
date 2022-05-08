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
        techyCacheMapper: TechyCacheMapper,
        techyNetworkMapper: TechyNetworkMapper
    ): MainRepository {
        return MainRepository(
            techyDao,
            techyService,
            techyCacheMapper,
            techyNetworkMapper
        )
    }

    @Singleton
    @Provides
    fun provideFavoritesRepository(
        techyDao: TechyDao,
        techyCacheMapper: TechyCacheMapper,
    ): FavoritesRepository {
        return FavoritesRepository(
            techyDao,
            techyCacheMapper,
        )
    }

    @Singleton
    @Provides
    fun provideNewRepository(
        techyDao: TechyDao,
        techyCacheMapper: TechyCacheMapper,
    ): NewRepository {
        return NewRepository(
            techyDao,
            techyCacheMapper,
        )
    }
}