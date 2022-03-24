package com.oliferov.shoppinglist.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("shop_item")
data class ShopItem(
    val name: String,
    val count: Int,
    val isActive: Boolean,
    @PrimaryKey
    var id: Int = DEFAULT_ID
){

    companion object{
        const val DEFAULT_ID = -1
    }
}