package com.example.wishlistapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wishlistapp.data.Wish
import com.example.wishlistapp.data.WishRepository
import com.example.wishlistapp.di.Graph
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class WishViewModel(
    private val wishRepository: WishRepository = Graph.wishRepository
) : ViewModel() {

    var wishTitleState by mutableStateOf("")
    var wishDescriptionState by mutableStateOf("")
    var wishImportantState by mutableStateOf(false)

    fun onWishTitleChange(newTitle: String) {
        wishTitleState = newTitle
    }

    fun onWishDescriptionChange(newTitle: String) {
        wishDescriptionState = newTitle
    }

    fun onWishImportantChange(newImportant: Boolean) {
        wishImportantState = newImportant
    }

    lateinit var getAllWishes: Flow<List<Wish>>

    init {
        viewModelScope.launch {
            getAllWishes = wishRepository.getAllWishes().map { wish ->
                wish.sortedWith(
                    compareByDescending<Wish> { it.important }
                        .thenByDescending { it.timestamp }
                )
            }
        }
    }

    fun getWishById(id: Long): Flow<Wish> {
        return wishRepository.getWishById(id)
    }

    fun addWish(wish: Wish) {
        viewModelScope.launch(context = Dispatchers.IO) {
            wishRepository.addWish(wish)
        }
    }

    fun updateWish(wish: Wish) {
        viewModelScope.launch(context = Dispatchers.IO) {
            wishRepository.updateWish(wish)
        }
    }

    fun deleteWish(wish: Wish) {
        viewModelScope.launch(context = Dispatchers.IO) {
            wishRepository.deleteWish(wish)
        }
    }
}