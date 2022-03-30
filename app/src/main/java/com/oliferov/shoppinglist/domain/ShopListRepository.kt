package com.oliferov.shoppinglist.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {

    fun getShopList(): LiveData<List<ShopItem>>

    fun getShopItem(shopItemId: Int): ShopItem

    fun removeShopItem(shopItem: ShopItem)

    fun addShopItem(shopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

}