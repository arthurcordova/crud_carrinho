package com.proway.crud.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.proway.crud.model.Category
import com.proway.crud.model.Product
import com.proway.crud.model.ProductWithCategory
import com.proway.crud.repository.CategoryRepository
import com.proway.crud.repository.ProductRepository

class ProductCRUDViewModel : ViewModel() {

    private val _products = MutableLiveData<List<ProductWithCategory>>()
    val products: LiveData<List<ProductWithCategory>> = _products

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> = _categories

    private val repository = ProductRepository()
    private val repositoryCategory = CategoryRepository()

    fun getProducts() {
        _products.value = repository.getProducts()
    }

    fun deleteProduct(product: Product) {
        repository.delete(product)
        getProducts()
    }

    fun updateProduct(product: Product) {
        repository.update(product)
        getProducts()
    }

    fun insertProduct(product: Product) {
        repository.insert(product)
        getProducts()
    }

    fun getCategories() {
        _categories.value = repositoryCategory.getCategories()
    }

}