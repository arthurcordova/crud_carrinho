package com.proway.crud.repository

import com.proway.crud.database.AppDatabase
import com.proway.crud.database.dao.CategoryDao
import com.proway.crud.model.Category

class CategoryRepository() {

    private val dao: CategoryDao = AppDatabase.getDatabase().categoryDao()

    fun getCategories(): List<Category> {
        return dao.getCategories()
    }

    fun insert(category: Category) {
        return dao.insert(arrayListOf(category))
    }

    fun update(category: Category) {
        return dao.update(category)
    }

    fun delete(category: Category) {
        return dao.delete(category)
    }


}