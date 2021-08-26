package com.proway.crud.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.proway.crud.model.Category

@Dao
interface CategoryDao {

    @Query("SELECT * FROM Category")
    fun getCategories() : List<Category>

    @Transaction
    @Insert
    fun insert(list: List<Category>)

}