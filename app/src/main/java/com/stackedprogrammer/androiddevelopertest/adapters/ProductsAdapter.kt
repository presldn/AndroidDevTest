package com.stackedprogrammer.androiddevelopertest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stackedprogrammer.androiddevelopertest.adapters.ProductsAdapter.ProductsViewHolder
import com.stackedprogrammer.androiddevelopertest.data.FavouriteProduct
import com.stackedprogrammer.androiddevelopertest.data.Product
import com.stackedprogrammer.androiddevelopertest.databinding.ProductListItemBinding

class ProductsAdapter(
    private val callback: ProductCallback
) : RecyclerView.Adapter<ProductsViewHolder>() {

    private lateinit var products: List<Product>

    class ProductsViewHolder(private val binding: ProductListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Product, callback: ProductCallback, productFavourite: Boolean) {
            binding.apply {
                product = item
                this.callback = callback
                isFavourite = productFavourite
                executePendingBindings()
            }
        }

    }

    private var favouriteProducts: List<FavouriteProduct>? = null

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {

        val product = products[position]
        holder.bind(product, callback, isProductFavourite(product.productId))

    }

    private fun isProductFavourite(productId: String): Boolean {

        if (favouriteProducts != null) {
            return favouriteProducts!!.any { it.productId == productId }
        }

        return false
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(
            ProductListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    fun setFavouriteProducts(products: List<FavouriteProduct>) {
        favouriteProducts = products
        notifyDataSetChanged()
    }

    interface ProductCallback {
        fun toggleFavourite(product: Product)
    }

    override fun getItemCount(): Int {
        return if (::products.isInitialized) products.size else 0
    }

    fun setProducts(products: List<Product>) {
        this.products = products
        notifyDataSetChanged()
    }
}
