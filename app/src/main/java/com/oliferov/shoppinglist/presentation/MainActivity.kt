package com.oliferov.shoppinglist.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.oliferov.shoppinglist.R
import com.oliferov.shoppinglist.domain.ShopItem
import com.oliferov.shoppinglist.presentation.ShopListAdapter.Companion.MAX_POOL_SIZE
import com.oliferov.shoppinglist.presentation.ShopListAdapter.Companion.VIEW_TYPE_DISABLED
import com.oliferov.shoppinglist.presentation.ShopListAdapter.Companion.VIEW_TYPE_ENABLED
import java.lang.RuntimeException

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var shopListAdapter: ShopListAdapter

    private val MODE_EDIT = "mode_edit"
    private val MODE_ADD = "mode_add"

    private var shopItemContainer: FragmentContainerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        shopItemContainer = findViewById(R.id.shop_item_container)
        setupRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            shopListAdapter.submitList(it)
        }
        val buttonAddItem = findViewById<FloatingActionButton>(R.id.button_add_shop_item)
        buttonAddItem.setOnClickListener {
            if (isOnePaneMode()) {
            startActivity(ShopItemActivity.newIntentAddItem(this))
            }else{
                launchFragment(ShopItemFragment.newInstanceAddItem())
            }
        }
    }

    private fun setupRecyclerView() {
        val rvShopList = findViewById<RecyclerView>(R.id.rv_shop_list)
        with(rvShopList) {
            shopListAdapter = ShopListAdapter()
            adapter = shopListAdapter
            recycledViewPool.setMaxRecycledViews(
                VIEW_TYPE_ENABLED,
                MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                VIEW_TYPE_DISABLED,
                MAX_POOL_SIZE
            )
        }
        setupLongClickListener()
        setupClickListener()
        setupSwipeListener(rvShopList)
    }

    private fun setupSwipeListener(rvShopList: RecyclerView) {
        var callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = shopListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.removeShopItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvShopList)
    }

    private fun setupClickListener() {
            shopListAdapter.onShopItemClickListener = {
                if (isOnePaneMode()) {
                    startActivity(ShopItemActivity.newIntentEditItem(this, it.id))
                } else {
                    launchFragment(ShopItemFragment.newInstanceEditItem(it.id))
                }
            }
    }

    private fun setupLongClickListener() {
        shopListAdapter.onShopItemLongClickListener = {
            viewModel.changeActiveState(it)
        }
    }

    private fun launchFragment(fragment: Fragment){
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.shop_item_container,fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun isOnePaneMode(): Boolean{
        return shopItemContainer == null
    }
}