package com.oliferov.shoppinglist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.oliferov.shoppinglist.R
import com.oliferov.shoppinglist.domain.ShopItem

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this){
            Log.i("DdD",it.toString())
        }
        viewModel.getShopList()
        viewModel.changeActiveState(ShopItem("name3",3.0,true,3))
        viewModel.removeShopItem(ShopItem("name4",4.0,true,4))
    }

}