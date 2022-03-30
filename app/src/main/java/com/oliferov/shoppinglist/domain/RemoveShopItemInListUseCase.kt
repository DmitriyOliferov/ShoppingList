package com.oliferov.shoppinglist.domain

class RemoveShopItemInListUseCase(private val shopListRepository: ShopListRepository) {
    fun removeShopItemInList(shopItem: ShopItem){
        shopListRepository.removeShopItem(shopItem)
    }
}