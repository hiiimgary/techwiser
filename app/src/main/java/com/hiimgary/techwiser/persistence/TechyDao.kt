package com.hiimgary.techwiser.persistence

import androidx.room.*

@Dao
interface TechyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favorite: TechyCacheEntity);

    @Query("SELECT * FROM techy")
    suspend fun getFavorites(): List<TechyCacheEntity>

    @Query("DELETE FROM techy where quote = :quote")
    suspend fun deleteFavorite(quote: String)

    @Query("UPDATE techy SET quote = :newQuote WHERE quote = :oldQuote")
    suspend fun updateFavorite(oldQuote: String, newQuote: String)

}