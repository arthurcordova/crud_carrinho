package com.proway.crud.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.proway.crud.model.Category
import com.proway.crud.model.Product
import com.proway.crud.model.ProductWithCategory

@Dao
interface ProductDao {

    @Transaction
    @Query("SELECT * FROM Product")
    fun getProducts(): List<ProductWithCategory>

    @Insert
    fun insert(product: Product)

    @Insert
    fun insert(category: Category): Long

    fun insert(productWithCategory: ProductWithCategory) {
        insert(productWithCategory.category!!)
        productWithCategory.product?.let { prod ->
            insert(prod)
        }
    }



}