package com.oliferov.shoppinglist.domain

class AddShopItemInListUseCase(private val shopListRepository: ShopListRepository) {
    fun addShopItemInList(shopItem: ShopItem){
        shopListRepository.addShopItemInList(shopItem)
    }
}