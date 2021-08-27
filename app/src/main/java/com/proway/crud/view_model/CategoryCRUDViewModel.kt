package com.proway.crud.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.proway.crud.model.Category
import com.proway.crud.repository.CategoryRepository

class CategoryCRUDViewModel : ViewModel() {

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> = _categories

    private val repository = CategoryRepository()

    fun getCategories() {
        _categories.value = repository.getCategories()
    }

    fun insertCategory(category: Category) {
        repository.insert(category)
        getCategories()
    }

    fun updateCategory(category: Category) {
        repository.update(category)
        getCategories()
    }

    fun deleteCategory(category: Category) {
        repository.delete(category)
        getCategories()
    }

}