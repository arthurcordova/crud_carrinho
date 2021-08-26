package com.proway.crud.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.proway.crud.model.Product
import com.proway.crud.model.ProductCategory

@Dao
interface ProductDao {

    @Transaction
    @Query("SELECT * FROM Product")
    fun getProducts() : List<ProductCategory>

    @Transaction
    @Insert
    fun insert(list: List<Product>)


}