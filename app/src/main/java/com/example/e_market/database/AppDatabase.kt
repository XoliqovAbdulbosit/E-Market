package com.example.e_market.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.e_market.database.dao.ProductDao
import com.example.e_market.database.entities.Product

@Database(entities = [Product::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        private var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, AppDatabase::class.java, "app_db")
                    .allowMainThreadQueries().build()
            }
            return instance!!
        }
    }

    abstract fun getProductDao(): ProductDao
}