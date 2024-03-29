package com.oliferov.shoppinglist.domain



data class ShopItem(
    val name: String,
    val count: Int,
    val isActive: Boolean,
    var id: Int = DEFAULT_ID
){

    companion object{
        const val DEFAULT_ID = 0
    }
}