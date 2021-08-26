package com.proway.crud.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.proway.crud.database.dao.CategoryDao
import com.proway.crud.database.dao.ProductDao
import com.proway.crud.model.Category
import com.proway.crud.model.Product

/**
 * Classe abstrata para criar nosso database local
 * Devemos passar na annotation @Database os parametros:
 * @param entities Array com a lista de classes com a annotation @Entity
 * @param version Int versao do banco (Quando qualquer coisa for alterada em relaçao
 * ao banco este numero deve ser incrementado)
 */
@Database(
    entities = [Product::class, Category::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    /**
     * funcao declarada para o Room implementar automaticamente nosso DAO
     */
    abstract fun categoryDao(): CategoryDao
    abstract fun productDao(): ProductDao


    companion object {

        /**
         * Singleton que irá gerar nossa classe AppDatabse com tudo implementado pelo Room.
         */
        fun getDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "crud_app_db"
            )
                .allowMainThreadQueries() // Utilizar esta linha quando NÃO utilizar coroutines
                .build()
        }
    }

}
