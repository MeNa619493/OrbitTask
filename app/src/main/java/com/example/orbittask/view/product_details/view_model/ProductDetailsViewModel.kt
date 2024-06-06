package com.example.orbittask.view.product_details.view_model

import androidx.lifecycle.ViewModel
import com.example.orbittask.domain.GetProductDetailsUseCase
import com.example.orbittask.domain.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val getProductDetailsUseCase: GetProductDetailsUseCase,
) : ViewModel(), ContainerHost<ProductDetailsState, Nothing> {

    override val container = container<ProductDetailsState, Nothing>(
        initialState = ProductDetailsState(loading = true)
    )

    fun loadOverview(id: Int) = intent {
        runCatching {
            val overview = getProductDetailsUseCase(id)

            reduce {
                state.copy(loading = false, data = overview)
            }
        }.onFailure { exception ->
            reduce {
                state.copy(loading = false, error = exception.message)
            }
        }
    }
}