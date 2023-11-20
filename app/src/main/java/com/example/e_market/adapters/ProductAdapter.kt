package com.example.e_market.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.e_market.R
import com.example.e_market.database.AppDatabase
import com.example.e_market.database.entities.Product

class ProductAdapter(context: Context, private val productPressed: ProductPressed) :
    RecyclerView.Adapter<ProductAdapter.MyHolder>() {
    val list = AppDatabase.getInstance(context).getProductDao().getAll().toMutableList()

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val product: ConstraintLayout = itemView.findViewById(R.id.product)
        val id: TextView = itemView.findViewById(R.id.id)
        val title: TextView = itemView.findViewById(R.id.title)
        val buy_price: TextView = itemView.findViewById(R.id.buy_price)
        val sell_price: TextView = itemView.findViewById(R.id.sell_price)
        val count: TextView = itemView.findViewById(R.id.count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val product = list[position]
        holder.id.text = product.id.toString()
        holder.title.text = product.name
        holder.buy_price.text = "${product.buyPrice}$"
        holder.sell_price.text = "${product.sellPrice}$"
        holder.count.text = product.count.toString()

        holder.product.setOnClickListener {
            productPressed.onPressed(product)
        }
    }

    interface ProductPressed {
        fun onPressed(product: Product)
    }
}