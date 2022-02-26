package com.oliferov.shoppinglist.domain

class GetShopItemInListUseCase(private val shopListRepository: ShopListRepository) {
    fun getShopItemInList(shopItemId: Int): ShopItem {
        return shopListRepository.getShopItemInList(shopItemId)
    }
}