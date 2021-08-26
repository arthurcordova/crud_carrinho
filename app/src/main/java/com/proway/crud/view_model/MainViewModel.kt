package com.proway.crud.view_model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.proway.crud.database.AppDatabase
import com.proway.crud.database.dao.CategoryDao
import com.proway.crud.model.Category

class MainViewModel : ViewModel() {

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> = _categories

    private lateinit var dao: CategoryDao

    fun injectContextAndStartDAO(context: Context) {
        dao = AppDatabase.getDatabase(context).categoryDao()
    }

    fun getCategories() {
        _categories.value = dao.getCategories()
    }

    fun insertCategory(category: Category) {
        dao.insert(arrayListOf(category))
        getCategories()
    }

    fun updateCategory(category: Category) {
        dao.update(category)
        getCategories()
    }

    fun deleteCategory(category: Category) {
        dao.delete(category)
        getCategories()
    }

}