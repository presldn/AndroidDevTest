package com.stackedprogrammer.androiddevelopertest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.stackedprogrammer.androiddevelopertest.R
import com.stackedprogrammer.androiddevelopertest.adapters.ProductsAdapter
import com.stackedprogrammer.androiddevelopertest.data.Product
import com.stackedprogrammer.androiddevelopertest.databinding.ProductListingFragmentBinding
import com.stackedprogrammer.androiddevelopertest.viewmodels.ProductListingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListingFragment : Fragment(), ProductsAdapter.ProductCallback {

    private lateinit var binding: ProductListingFragmentBinding

    private val viewModel: ProductListingViewModel by viewModels()
    private var snackbar: Snackbar? = null


    companion object {
        fun newInstance() = ProductListingFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = ProductListingFragmentBinding.inflate(inflater, container, false)

        val adapter = ProductsAdapter(this)
        binding.productList.adapter = adapter

        subscribeUi(adapter)

        return binding.root
    }

    private fun subscribeUi(adapter: ProductsAdapter) {

        viewModel.products.observe(viewLifecycleOwner) { products ->
            adapter.setProducts(products)
        }

        viewModel.loadingVisibility.observe(viewLifecycleOwner) { visibility ->
            binding.loadingVisibility = visibility
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        }

        viewModel.productFaveMessage.observe(viewLifecycleOwner) { likedMessage ->
            if (likedMessage != null) showMessage(likedMessage)
        }

        viewModel.favouriteProducts.observe(viewLifecycleOwner) { faveProducts ->
            adapter.setFavouriteProducts(faveProducts)
        }

    }

    private fun showMessage(likedMessage: Int) {
        snackbar = Snackbar.make(binding.root, likedMessage, Snackbar.LENGTH_SHORT)
        snackbar?.show()
    }

    private fun hideError() {
        snackbar?.dismiss()
    }

    private fun showError(errorMessage: Int) {
        snackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        snackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        snackbar?.show()
    }

    override fun toggleFavourite(product: Product) {
        viewModel.toggleProduct(product)
    }

}