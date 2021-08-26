package com.proway.crud.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.proway.crud.database.AppDatabase
import com.proway.crud.model.Category
import com.proway.crud.model.Product
import com.proway.crud.model.ProductWithCategory
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class ProductDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: ProductDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.productDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insert_category_returns_true() {
        val c1 = Category(10L, "Eletronico")
        val p1 = Product(20L, "TV", 100.0, c1.id)
        val productWithCategory = ProductWithCategory(product = p1, category = c1)

        dao.insert(productWithCategory)



        val results = dao.getProducts()
        Truth.assertThat(results).contains(productWithCategory)
    }

}