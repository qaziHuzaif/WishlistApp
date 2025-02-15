package com.example.wishlistapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.wishlistapp.data.WishDatabase
import com.example.wishlistapp.data.WishRepository

object Graph {
    private lateinit var database: WishDatabase

    val wishRepository by lazy {
        WishRepository(database.wishDAO())
    }

    private val MITIGATION_1_2 = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("ALTER TABLE \"wish-table\" ADD COLUMN \"wish-important\" INTEGER NOT NULL DEFAULT 0")
            db.execSQL("ALTER TABLE \"wish-table\" ADD COLUMN \"wish-date\" INTEGER NOT NULL DEFAULT 0")
        }
    }

    fun provide(context: Context) {
        database = Room
            .databaseBuilder(context, WishDatabase::class.java, "wishlist.db")
            .addMigrations(MITIGATION_1_2)
            .build()
    }
}