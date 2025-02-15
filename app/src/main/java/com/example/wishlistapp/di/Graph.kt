package com.example.wishlistapp.di

import android.content.Context
import androidx.room.Room
import com.example.wishlistapp.data.WishDatabase
import com.example.wishlistapp.data.WishRepository

object Graph {
    private lateinit var database: WishDatabase

    val wishRepository by lazy {
        WishRepository(database.wishDAO())
    }

    fun provide(context: Context) {
        database = Room.databaseBuilder(context, WishDatabase::class.java, "wishlist.db").build()
    }
}