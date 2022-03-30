package com.oliferov.shoppinglist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oliferov.shoppinglist.data.ShopListRepositoryImpl
import com.oliferov.shoppinglist.domain.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val shopListRepositoryImpl = ShopListRepositoryImpl(application)

    private val getShopListUseCase = GetShopListUseCase(shopListRepositoryImpl)
    private val editShopItemUseCase = EditShopItemUseCase(shopListRepositoryImpl)
    private val removeShopItemInListUseCase = RemoveShopItemInListUseCase(shopListRepositoryImpl)

    val shopList = getShopListUseCase.getShopList()

    fun changeActiveState(shopItem: ShopItem){
        viewModelScope.launch {
            val shopItemCopy = when (shopItem.isActive) {
                true -> shopItem.copy(isActive = false)
                false -> shopItem.copy(isActive = true)
            }
            editShopItemUseCase.editShopItem(shopItemCopy)
        }
    }

    fun removeShopItem(shopItem: ShopItem) {
        viewModelScope.launch {
            removeShopItemInListUseCase.removeShopItemInList(shopItem)
        }
    }
}