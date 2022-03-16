package com.oliferov.shoppinglist.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {

    fun getShopList(): LiveData<List<ShopItem>>

    fun getShopItemInList(shopItemId: Int): ShopItem

    fun removeShopItemInList(shopItem: ShopItem)

    fun addShopItemInList(shopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

}