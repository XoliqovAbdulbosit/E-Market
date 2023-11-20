package com.example.e_market.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.e_market.R
import com.example.e_market.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.button1.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_databaseFragment)
        }

        binding.button2.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_customersFragment)
        }

        return binding.root
    }
}