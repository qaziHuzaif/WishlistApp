package com.example.wishlistapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Wish::class],
    version = 2,
    exportSchema = false
)
abstract class WishDatabase : RoomDatabase() {
    abstract fun wishDAO(): WishDAO
}