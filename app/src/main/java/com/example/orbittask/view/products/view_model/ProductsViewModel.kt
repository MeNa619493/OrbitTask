package com.example.orbittask.view.products.view_model

import androidx.lifecycle.ViewModel
import com.example.orbittask.domain.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
) : ViewModel(), ContainerHost<ProductListState, NavigationEvent> {

    override val container = container<ProductListState, NavigationEvent>(
        initialState = ProductListState(loading = true)
    )

    fun loadOverviews() = intent {
        runCatching {
            val overviews = getProductsUseCase()

            reduce {
                state.copy(loading = false, data = overviews.products)
            }
        }.onFailure { exception ->
            reduce {
                state.copy(loading = false, error = exception.message)
            }
        }
    }
}