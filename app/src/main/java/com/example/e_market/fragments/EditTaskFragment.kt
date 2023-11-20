package com.example.e_market.fragments

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
import com.example.e_market.databinding.FragmentEditTaskBinding

private const val ARG_PARAM = "id"

class EditTaskFragment : Fragment() {
    private var param: Int? = null

    private val appDataBase: AppDatabase by lazy {
        AppDatabase.getInstance(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param = it.getInt(ARG_PARAM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentEditTaskBinding.inflate(inflater, container, false)
        val id = param
        val product: Product = appDataBase.getProductDao().getProduct(id)

        binding.title.setText(product.name)
        binding.buyPrice.setText(product.buyPrice.toString())
        binding.sellPrice.setText(product.sellPrice.toString())
        binding.count.setText(product.count.toString())

        binding.btn.setOnClickListener {
            product.name = binding.title.text.toString()
            product.buyPrice = binding.buyPrice.text.toString().toDouble()
            product.sellPrice = binding.sellPrice.text.toString().toDouble()
            product.count = binding.count.text.toString().toInt()

            appDataBase.getProductDao().updateProduct(product)

            Toast.makeText(requireContext(), "Product edited", Toast.LENGTH_LONG).show()
            val bundle = Bundle()
            bundle.putSerializable("product", product)
            findNavController().navigate(R.id.action_editTaskFragment_to_productFragment, bundle)
        }

        return binding.root
    }
}