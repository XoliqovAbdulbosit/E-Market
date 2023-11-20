package com.example.e_market.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_market.R
import com.example.e_market.adapters.CardAdapter
import com.example.e_market.database.AppDatabase
import com.example.e_market.databinding.FragmentCustomersBinding
import com.example.e_market.utils.CardProduct
import com.example.e_market.utils.MySharedPreference

class CustomersFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCustomersBinding.inflate(inflater, container, false)

        binding.card.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        val adapter = CardAdapter(requireContext())
        binding.card.adapter = adapter


        binding.add.setOnClickListener {
            val shared = MySharedPreference(requireContext())
            val data = AppDatabase.getInstance(requireContext()).getProductDao()
                .getProduct(binding.id.text.toString().toInt())
            adapter.update(shared.addProduct(CardProduct(data.id, data.name, data.sellPrice, 1)))
        }

        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_customersFragment_to_mainFragment)
        }

        return binding.root
    }
}