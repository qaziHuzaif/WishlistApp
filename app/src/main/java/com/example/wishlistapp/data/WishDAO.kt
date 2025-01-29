package com.example.wishlistapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
abstract class WishDAO {

    @Insert
    abstract suspend fun addWish(wishEntity: Wish)

    @Update
    abstract suspend fun updateWish(wishEntity: Wish)

    @Query("Select * from `wish-table`")
    abstract fun getAllWishes(): Flow<List<Wish>>

    @Query("Select * from `wish-table` where id=:id")
    abstract fun getWishById(id: Long): Flow<Wish>

    @Delete
    abstract suspend fun deleteWish(wishEntity: Wish)
}