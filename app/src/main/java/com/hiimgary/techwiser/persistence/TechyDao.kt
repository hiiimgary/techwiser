package com.hiimgary.techwiser.persistence

import androidx.room.*

@Dao
interface TechyDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorite(favorite: TechyCacheEntity);

    @Query("SELECT * FROM techy")
    suspend fun getFavorites(): List<TechyCacheEntity>

    @Delete()
    suspend fun deleteFavorite(favorite: TechyCacheEntity)


}