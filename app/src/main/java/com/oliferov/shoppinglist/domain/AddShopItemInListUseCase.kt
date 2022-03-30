package com.oliferov.shoppinglist.domain

class AddShopItemInListUseCase(private val shopListRepository: ShopListRepository) {
    suspend fun addShopItemInList(shopItem: ShopItem){
        shopListRepository.addShopItem(shopItem)
    }
}