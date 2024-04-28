package com.mitocode.ecoats.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mitocode.ecoats.data.database.dao.DishDao
import com.mitocode.ecoats.data.database.dao.FavoriteDao
import com.mitocode.ecoats.data.database.dao.UserDao
import com.mitocode.ecoats.data.database.model.DishEntity
import com.mitocode.ecoats.data.database.model.FavoriteEntity
import com.mitocode.ecoats.data.database.model.UserEntity

@Database(entities = [UserEntity::class, DishEntity::class, FavoriteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dishDao() : DishDao

    abstract fun userDao() : UserDao

    abstract fun favoriteDao() : FavoriteDao

}