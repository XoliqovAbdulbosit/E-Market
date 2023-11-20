package com.example.e_market.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_market.R
import com.example.e_market.adapters.ProductAdapter
import com.example.e_market.database.entities.Product
import com.example.e_market.databinding.FragmentDatabaseBinding

class DatabaseFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDatabaseBinding.inflate(inflater, container, false)

        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_databaseFragment_to_mainFragment)
        }

        binding.products.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.products.adapter =
            ProductAdapter(requireContext(), object : ProductAdapter.ProductPressed {
                override fun onPressed(product: Product) {
                    val bundle = Bundle()
                    bundle.putSerializable("product", product)
                    findNavController().navigate(
                        R.id.action_databaseFragment_to_productFragment, bundle
                    )
                }
            })

        binding.add.setOnClickListener {
            findNavController().navigate(R.id.action_databaseFragment_to_addProductFragment)
        }

        return binding.root
    }
}