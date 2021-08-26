package com.proway.crud.repository

import android.content.Context
import com.proway.crud.database.AppDatabase
import com.proway.crud.database.dao.CategoryDao
import com.proway.crud.model.Category

class CategoryRepository(val context: Context) {

    private val dao: CategoryDao = AppDatabase.getDatabase(context).categoryDao()

    fun getCategories(): List<Category> {
        return dao.getCategories()
    }

    fun insert(category: Category) {
        return dao.insert(arrayListOf(category))
    }


}