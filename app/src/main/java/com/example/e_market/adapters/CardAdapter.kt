package com.example.e_market.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_market.R
import com.example.e_market.utils.CardProduct
import com.example.e_market.utils.MySharedPreference
import com.google.android.material.button.MaterialButton

class CardAdapter(context: Context) : RecyclerView.Adapter<CardAdapter.MyHolder>() {
    private val shared = MySharedPreference(context)
    private var list = shared.getProducts()

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.card_title)
        val price: TextView = itemView.findViewById(R.id.card_price)
        val plus: MaterialButton = itemView.findViewById(R.id.plus)
        val minus: MaterialButton = itemView.findViewById(R.id.minus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_product_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val product = list[position]
        holder.title.text = product.name
        holder.price.text = "${product.count} * ${product.price} = ${product.count * product.price}"

        holder.plus.setOnClickListener {
            list = shared.changeProduct(position, 1)
            notifyItemChanged(position)
        }

        holder.minus.setOnClickListener {
            list = shared.changeProduct(position, -1)
            notifyItemChanged(position)
        }
    }

    fun update(updated: List<CardProduct>) {
        list = updated
        notifyItemInserted(updated.size - 1)
    }
}