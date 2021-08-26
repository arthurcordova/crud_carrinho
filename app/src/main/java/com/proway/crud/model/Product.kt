package com.proway.crud.model

import androidx.room.*

@Entity
data class Product(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "prod_id")
    val id: Long,
    @ColumnInfo(name = "prod_name")
    val name: String,
    @ColumnInfo(name = "prod_price")
    val price: Double,

)

data class ProductCategory(
    @Embedded
    val product: Product,
    @Relation(
        parentColumn = "prod_id",
        entityColumn = "cat_id"
    )
    val category: Category
)
