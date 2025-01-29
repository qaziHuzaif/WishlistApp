package com.example.wishlistapp.data

import kotlinx.coroutines.flow.Flow

class WishRepository(private val wishDAO: WishDAO) {

    suspend fun addWish(wish: Wish){
        wishDAO.addWish(wish)
    }

    suspend fun updateWish(wish: Wish){
        wishDAO.updateWish(wish)
    }

    fun getAllWishes(): Flow<List<Wish>> {
        return wishDAO.getAllWishes()
    }

    fun getWishById(id: Long): Flow<Wish> {
        return wishDAO.getWishById(id)
    }

    suspend fun deleteWish(wish: Wish) {
        wishDAO.deleteWish(wish)
    }
}