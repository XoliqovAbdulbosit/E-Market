package com.example.e_market.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.e_market.R
import com.example.e_market.database.AppDatabase
import com.example.e_market.database.entities.Product
import com.example.e_market.databinding.FragmentAddProductBinding

class AddProductFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAddProductBinding.inflate(inflater, container, false)

        binding.btn.setOnClickListener {
            val product = Product(
                name = binding.title.text.toString(),
                buyPrice = binding.buyPrice.text.toString().toDouble(),
                sellPrice = binding.sellPrice.text.toString().toDouble(),
                count = binding.count.text.toString().toInt(),
            )

            val appDatabase = AppDatabase.getInstance(requireContext())
            appDatabase.getProductDao().addProduct(product)

            findNavController().navigate(R.id.action_addProductFragment_to_databaseFragment)
        }

        return binding.root
    }
}