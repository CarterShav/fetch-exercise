/*
    Item Class created to provide element for recycler view.
    author: Carter Shavitz
    date: 7/9/2024
 */
package com.example.fetchcodingexercise

/**
 * Item class contains Header data class to contain information provided to the header, as well as
 * Content class for the data of the recycler view.
 */
sealed class Item {
    data class Header(val idHeader: String, val listHeader: String, val nameHeader: String) : Item()
    data class Content(val id: Int, val listId: Int, val name: String) : Item()
}