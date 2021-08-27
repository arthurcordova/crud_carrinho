package com.proway.crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.proway.crud.databinding.MainActivityBinding
import com.proway.crud.utils.replaceFragment
import com.proway.crud.view.CategoryCRUDFragment
import com.proway.crud.view.LoginFragment
import com.proway.crud.view.ProductCRUDFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        settingBottomBar()
        settingInitFrag()
    }

    fun settingInitFrag() {
        replaceFragment(fragment = ProductCRUDFragment.newInstance())
    }

    fun settingBottomBar() {
        binding.bottomBarNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_products -> {
                    replaceFragment(fragment = ProductCRUDFragment.newInstance())
                }
                R.id.nav_categories -> {
                    replaceFragment(fragment = CategoryCRUDFragment.newInstance())
                }
                R.id.nav_cart -> {
                    replaceFragment(fragment = LoginFragment.newInstance())
                }
            }
            true
        }
    }
}