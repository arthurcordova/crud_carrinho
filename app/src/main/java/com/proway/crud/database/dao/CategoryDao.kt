package com.proway.crud.database.dao

import androidx.room.*
import com.proway.crud.model.Category

@Dao
interface CategoryDao {

    @Query("SELECT * FROM Category")
    fun getCategories(): List<Category>

    @Query("SELECT * FROM Category WHERE cat_id = :id")
    fun getCategory(id: Long): Category

    @Insert
    fun insert(list: List<Category>)

    @Delete
    fun delete(category: Category)

    @Update
    fun update(category: Category)
}