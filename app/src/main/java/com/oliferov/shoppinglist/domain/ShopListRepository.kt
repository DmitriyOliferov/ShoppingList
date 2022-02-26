package com.oliferov.shoppinglist.domain

interface ShopListRepository {

    fun getShopList(): List<ShopItem>

    fun getShopItemInList(shopItemId: Int): ShopItem

    fun removeShopItemInList(shopItem: ShopItem)

    fun addShopItemInList(shopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

}