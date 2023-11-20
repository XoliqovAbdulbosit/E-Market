package com.example.e_market.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.e_market.R
import com.example.e_market.database.AppDatabase
import com.example.e_market.database.entities.Product
import com.example.e_market.databinding.FragmentProductBinding
import kotlin.math.round

private const val ARG_PARAM = "product"

class ProductFragment : Fragment() {
    private lateinit var product: Product
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { product = it.getSerializable(ARG_PARAM) as Product }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentProductBinding.inflate(inflater, container, false)

        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_productFragment_to_databaseFragment)
        }

        binding.title.text = product.name
        binding.buyPrice.text = "Buy price: ${product.buyPrice}$"
        binding.sellPrice.text = "Sell price: ${product.sellPrice}$"
        binding.profit.text = "Profit: ${(product.sellPrice - product.buyPrice)}$"
        binding.margin.text =
            "Marginality: ${round(((product.sellPrice - product.buyPrice) / product.buyPrice) * 100).toInt()}%"
        binding.count.text = "Count: ${product.count}"

        binding.edit.setOnClickListener {
            val fragment = EditTaskFragment()
            val bundle = Bundle()
            bundle.putInt("id", product.id)
            fragment.arguments = bundle
            findNavController().navigate(R.id.action_productFragment_to_editTaskFragment, bundle)
        }

        binding.delete.setOnClickListener {
            AppDatabase.getInstance(requireContext()).getProductDao().deleteProduct(product)
            Toast.makeText(requireContext(), "Product deleted", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_productFragment_to_databaseFragment)
        }

        return binding.root
    }
}