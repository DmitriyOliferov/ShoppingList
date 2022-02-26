package com.oliferov.shoppinglist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oliferov.shoppinglist.data.ShopListRepositoryImpl
import com.oliferov.shoppinglist.domain.*

class MainViewModel: ViewModel() {
    private val shopListRepositoryImpl = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(shopListRepositoryImpl)
    private val editShopItemUseCase = EditShopItemUseCase(shopListRepositoryImpl)
    private val removeShopItemInListUseCase = RemoveShopItemInListUseCase(shopListRepositoryImpl)

    val shopList = MutableLiveData<List<ShopItem>>()

    fun getShopList() {
        shopList.postValue(getShopListUseCase.getShopList())
    }

    fun changeActiveState(shopItem: ShopItem){
        val shopItemCopy = shopItem.copy(isActive = false)
        editShopItemUseCase.editShopItem(shopItemCopy)
        getShopList()
    }

    fun removeShopItem(shopItem: ShopItem) {
        removeShopItemInListUseCase.removeShopItemInList(shopItem)
        getShopList()

    }
}