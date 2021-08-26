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
    fun getProducts() : List<ProductWithCategory>

    @Transaction
    @Insert
    fun insert(list: List<Product>)


    fun insert(productWithCategory: ProductWithCategory) {
        insert(productWithCategory.category!!)
        productWithCategory.product?.let {
            insert(listOf(it))
        }
    }

    @Insert
    fun insert(category: Category) : Long

}