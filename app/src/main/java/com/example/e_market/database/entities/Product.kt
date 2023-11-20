package com.example.e_market.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Products")
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var name: String,
    var buyPrice: Double,
    var sellPrice: Double,
    var count: Int,
) : Serializable