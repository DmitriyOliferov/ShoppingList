package com.oliferov.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.oliferov.shoppinglist.domain.ShopItem
import com.oliferov.shoppinglist.domain.ShopListRepository
import java.lang.Exception
import kotlin.random.Random

object ShopListRepositoryImpl : ShopListRepository {

    private val shopListLD = MutableLiveData<List<ShopItem>>()
    private var shopItemList= sortedSetOf<ShopItem>({ o1, o2 -> o1.id.compareTo(o2.id)})

    private var autoIncrementId = 0

    init {
        for (i in  0..10){
            val item = ShopItem("name$i", i.toInt(), Random.nextBoolean())
            addShopItemInList(item)
        }
    }
    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    override fun getShopItemInList(shopItemId: Int): ShopItem {
        return shopItemList.find { it.id == shopItemId }
            ?: throw Exception("Not find element with id $shopItemId")
    }

    override fun removeShopItemInList(shopItem: ShopItem) {
        shopItemList.remove(shopItem)
        updateList()
    }

    override fun addShopItemInList(shopItem: ShopItem) {
        if(shopItem.id == ShopItem.DEFAULT_ID) {
            shopItem.id = autoIncrementId++
        }
        shopItemList.add(shopItem)
        updateList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        shopItemList.remove(getShopItemInList(shopItem.id))
        shopItemList.add(shopItem)
        updateList()
    }

    private fun updateList() {
        shopListLD.value = shopItemList.toList()
    }
}