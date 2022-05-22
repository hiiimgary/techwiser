package com.hiimgary.techwiser.persistence

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [
    TechyCacheEntity::class
], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun techyDao(): TechyDao

    companion object {
        val DATABASE_NAME: String = "techy_db";
    }
}