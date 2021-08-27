package com.proway.crud.database.dao

import androidx.room.*
import com.proway.crud.model.Category
import com.proway.crud.model.Product
import com.proway.crud.model.ProductWithCategory

@Dao
interface ProductDao {

    @Transaction
    @Query("SELECT * FROM Product")
    fun getProducts(): List<ProductWithCategory>

    @Transaction
    @Query("SELECT * FROM Product WHERE prod_id = :id")
    fun getById(id: Long): ProductWithCategory

    @Insert
    fun insert(product: Product)

    @Delete
    fun delete(product: Product)

    @Update
    fun update(product: Product)

}