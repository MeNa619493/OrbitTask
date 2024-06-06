package com.example.orbittask.view.products.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.orbittask.data.models.Product
import com.example.orbittask.databinding.FragmentProductsBinding
import com.example.orbittask.view.products.adapter.ProductsAdapter
import com.example.orbittask.view.products.view_model.NavigationEvent
import com.example.orbittask.view.products.view_model.ProductListState
import com.example.orbittask.view.products.view_model.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

@AndroidEntryPoint
class ProductsFragment : Fragment() {
    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductsViewModel by viewModels()
    private val adapter by lazy {
        ProductsAdapter {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvProducts.adapter = adapter

        viewModel.observe(viewLifecycleOwner, state = ::render, sideEffect = ::sideEffect)
        viewModel.loadOverviews()
    }

    private fun render(state: ProductListState) {
        handleProgressbar(state.loading)
        handleSuccess(state.data)
        handleError(state.error)
    }

    private fun handleProgressbar(isVisible: Boolean) {
        binding.apply {
            if (isVisible) {
                progress.visibility = View.VISIBLE
            } else {
                progress.visibility = View.GONE
            }
        }
    }

    private fun handleSuccess(data: List<Product>?) {
        binding.apply {
            if (data.isNullOrEmpty()) {
                rvProducts.visibility = View.GONE
            } else {
                rvProducts.visibility = View.VISIBLE
                adapter.submitList(data)
            }
        }
    }

    private fun handleError(error: String?) {
        binding.apply {
            if (!error.isNullOrEmpty()) {
                tvEmpty.visibility = View.VISIBLE
                tvEmpty.text = error
            }
        }
    }

    private fun sideEffect(sideEffect: NavigationEvent) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}