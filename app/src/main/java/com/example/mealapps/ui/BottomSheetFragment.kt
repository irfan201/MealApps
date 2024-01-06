package com.example.mealapps.ui

// BottomSheetFragment.kt

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealapps.adapter.AreaAdapter
import com.example.mealapps.adapter.CategoryAdapter
import com.example.mealapps.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment : BottomSheetDialogFragment() {

    private val viewModel by lazy { MainViewModel() }
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var areaAdapter: AreaAdapter

    private var _binding: FragmentBottomSheetBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryAdapter = CategoryAdapter()
        areaAdapter = AreaAdapter()

        binding.rvCategory.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCategory.adapter = categoryAdapter

        binding.rvArea.layoutManager = LinearLayoutManager(requireContext())
        binding.rvArea.adapter = areaAdapter


        viewModel.areas.observe(viewLifecycleOwner) { areas ->
            Log.d("BottomSheetFragment", "apa ada: ${areas.size}")
            areaAdapter.submitList(areas)
        }
        viewModel.getAreas()
        areaAdapter.setOnItemClickListener { area ->
            (activity as? MainActivity)?.onAreaClicked(area)
            dismiss()
        }


        viewModel.categories.observe(viewLifecycleOwner) { categories ->
            Log.d("BottomSheetFragment", "apa ada: ${categories.size}")
            categoryAdapter.submitList(categories)
        }
        viewModel.getCategories()
        categoryAdapter.setOnItemClickListener { category ->
            (activity as? MainActivity)?.onCategoryClicked(category)
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Release the binding when the fragment is destroyed
    }
}

