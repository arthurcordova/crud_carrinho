package com.proway.crud.utils

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.proway.crud.R

fun AppCompatActivity.replaceFragment(
    @IdRes idComponent: Int = R.id.container,
    fragment: Fragment
) {
    supportFragmentManager.beginTransaction()
        .replace(idComponent, fragment)
        .commitNow()
}