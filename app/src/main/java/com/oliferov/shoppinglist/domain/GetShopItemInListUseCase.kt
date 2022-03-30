package com.oliferov.shoppinglist.domain

class GetShopItemInListUseCase(private val shopListRepository: ShopListRepository) {
    suspend fun getShopItemInList(shopItemId: Int): ShopItem {
        return shopListRepository.getShopItem(shopItemId)
    }
}