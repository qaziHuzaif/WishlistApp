package com.example.wishlistapp.di

import android.app.Application

class WishlistApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}