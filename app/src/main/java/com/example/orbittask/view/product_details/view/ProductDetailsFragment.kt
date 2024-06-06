package com.example.orbittask.view.product_details.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.orbittask.R
import com.example.orbittask.data.models.Product
import com.example.orbittask.databinding.FragmentProductDetailsBinding
import com.example.orbittask.databinding.FragmentProductsBinding
import com.example.orbittask.view.product_details.adapter.ViewPagesAdapter
import com.example.orbittask.view.product_details.view_model.ProductDetailsState
import com.example.orbittask.view.product_details.view_model.ProductDetailsViewModel
import com.example.orbittask.view.products.view_model.ProductListState
import com.example.orbittask.view.products.view_model.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {
    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.observe(viewLifecycleOwner, state = ::render)
        setAction()
    }

    private fun setAction() {
        val id = arguments?.getInt("id")
        viewModel.loadOverview(id ?: 0)
    }

    private fun render(state: ProductDetailsState) {
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

    private fun handleSuccess(product: Product?) {
        binding.apply {
            product?.let {
                viewData.visibility = View.VISIBLE
                if ((it.images?.size ?: 0) > 1) {
                    gradientOverlay.setViewPager(vpImages)
                } else {
                    gradientOverlay.visibility = View.GONE
                }
                vpImages.adapter = ViewPagesAdapter(product.images ?: emptyList())
                vpImages.orientation = ViewPager2.ORIENTATION_HORIZONTAL
                tvTitle.text = product.title
                tvPrice.text = "Price: ${product.price}$"
                tvDesc.text = product.description
            }
        }
    }

    private fun handleError(error: String?) {
        binding.apply {
            if (!error.isNullOrEmpty()) {
                tvError.visibility = View.VISIBLE
                tvError.text = error
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}