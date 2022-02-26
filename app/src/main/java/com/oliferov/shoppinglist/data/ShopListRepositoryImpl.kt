package com.oliferov.shoppinglist.data

import com.oliferov.shoppinglist.domain.ShopItem
import com.oliferov.shoppinglist.domain.ShopListRepository
import java.lang.Exception

object ShopListRepositoryImpl : ShopListRepository {

    private var shopItemList = mutableListOf<ShopItem>()

    private var autoIncrementId = 0

    init {
        for (i in  0..5){
            val item = ShopItem("name$i", i.toDouble(), true)
            addShopItemInList(item)
        }
    }
    override fun getShopList(): List<ShopItem> {
        return shopItemList.toList()
    }

    override fun getShopItemInList(shopItemId: Int): ShopItem {
        return shopItemList.find { it.id == shopItemId }
            ?: throw Exception("Not find element with id $shopItemId")
    }

    override fun removeShopItemInList(shopItem: ShopItem) {
        shopItemList.remove(shopItem)
    }

    override fun addShopItemInList(shopItem: ShopItem) {
        if(shopItem.id == ShopItem.DEFAULT_ID) {
            shopItem.id = autoIncrementId++
        }
        shopItemList.add(shopItem)
    }

    override fun editShopItem(shopItem: ShopItem) {
        shopItemList.remove(getShopItemInList(shopItem.id))
        shopItemList.add(shopItem)
    }
}