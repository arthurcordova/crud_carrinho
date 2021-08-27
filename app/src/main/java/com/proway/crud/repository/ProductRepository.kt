package com.proway.crud.repository

import com.proway.crud.database.AppDatabase
import com.proway.crud.database.dao.ProductDao
import com.proway.crud.model.Product
import com.proway.crud.model.ProductWithCategory

class ProductRepository {

    private val dao: ProductDao = AppDatabase.getDatabase().productDao()

    fun getProducts(): List<ProductWithCategory> {
        return dao.getProducts()
    }

    fun insert(product: Product) {
        return dao.insert(product)
    }

    fun delete(product: Product) {
        return dao.delete(product)
    }

    fun update(product: Product) {
        return dao.update(product)
    }

}