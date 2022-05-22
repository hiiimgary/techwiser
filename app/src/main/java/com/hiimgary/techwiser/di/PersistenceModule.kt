package com.hiimgary.techwiser.di

import android.content.Context
import androidx.room.Room
import com.hiimgary.techwiser.persistence.AppDatabase
import com.hiimgary.techwiser.persistence.TechyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Singleton
    @Provides
    fun provideAppDb(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideTechyDao(appDatabase: AppDatabase): TechyDao {
        return appDatabase.techyDao()
    }
}