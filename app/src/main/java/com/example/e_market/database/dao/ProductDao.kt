package com.example.e_market.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.e_market.database.entities.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM Products")
    fun getAll(): List<Product>

    @Query("SELECT * FROM Products WHERE id = :id")
    fun getProduct(id: Int?): Product

    @Insert
    fun addProduct(newProduct: Product)

    @Delete
    fun deleteProduct(product: Product)

    @Update
    fun updateProduct(product: Product)
}