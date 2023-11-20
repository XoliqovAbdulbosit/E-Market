package com.example.e_market.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MySharedPreference(context: Context) {
    private val shared = context.getSharedPreferences("data", 0)
    private val edit = shared.edit()
    private val gson = Gson()

    private val card = "card"

    fun getProducts(): List<CardProduct> {
        val data = shared.getString(card, "")
        if (data == "") return listOf()
        val typeToken = object : TypeToken<List<CardProduct>>() {}.type
        return gson.fromJson(data, typeToken)
    }

    fun addProduct(product: CardProduct): List<CardProduct> {
        val products = getProducts().toMutableList()
        products.add(product)
        edit.putString(card, gson.toJson(products)).apply()
        return products
    }

    fun changeProduct(id: Int, count: Int): List<CardProduct> {
        val products = getProducts().toMutableList()
        products[id].count += count
        edit.putString(card, gson.toJson(products)).apply()
        return products
    }
}