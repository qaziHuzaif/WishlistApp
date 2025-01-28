package com.example.wishlistapp.data

data class Wish(
    val id: Long = 0L,
    val title: String = "",
    val description: String = ""
)

object DummyWish{
    val list = listOf(
        Wish(
            title = "Item 1",
            description = "ofsdalfjsadfljasdf"
        ),
        Wish(
            title = "Item 2",
            description = "sjalfdjalfsdjafdlskdfaljksfdjlks"
        ),
        Wish(
            title = "Item 3",
            description = ";fsdalkoeesfjldjlsfj"
        )
    )
}