package com.stackedprogrammer.androiddevelopertest.viewmodels

import android.view.View
import androidx.lifecycle.*
import com.stackedprogrammer.androiddevelopertest.R
import com.stackedprogrammer.androiddevelopertest.data.*
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ProductListingViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val favouriteProductRepository: FavouriteProductRepository
) : ViewModel() {

    private lateinit var subscription: Disposable

    var products: MutableLiveData<List<Product>> = MutableLiveData()

    val favouriteProducts: LiveData<List<FavouriteProduct>> =
        favouriteProductRepository.getFavouriteProducts().asLiveData()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val productFaveMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { findProducts() }

    init {
        findProducts()
    }

    override fun onCleared() {
        subscription.dispose()
        super.onCleared()
    }

    private fun findProducts() {

        subscription =  productRepository.getProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onFindProductsStart() }
            .doOnTerminate { onFindProductsFinish() }
            .subscribe(
                { result ->  onFindProductsSuccess(result) },
                { error -> onFindProductsError(error) }
            )

    }

    private fun onFindProductsSuccess(result: ProductResponse) {
        products.value = result.summaries
    }

    private fun onFindProductsError(error: Throwable) {
        println(error.message)
        errorMessage.value = R.string.load_product_error
    }

    private fun onFindProductsFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onFindProductsStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    fun toggleProduct(product: Product) {

        val favouriteProduct: FavouriteProduct? = favouriteProducts.value?.find {
            it.productId == product.productId
        }

        if (favouriteProduct != null) {
            removeFavouriteProduct(favouriteProduct)
        } else {
            addFavouriteProduct(product)
        }
    }

    private fun onUpdateSuccess(favorite: Boolean) {

        if (favorite)
            productFaveMessage.value = R.string.product_fave_message
        else
            productFaveMessage.value = null

    }

    private fun addFavouriteProduct(product: Product) {

        subscription = favouriteProductRepository.addFavouriteProduct(
            product.productId, product.name,
            product.price.amount, product.images.urlTemplate)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { onUpdateSuccess(true) }

    }

    private fun removeFavouriteProduct(favouriteProduct: FavouriteProduct) {

        subscription = favouriteProductRepository.removeFavouriteProduct(favouriteProduct)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { onUpdateSuccess(false) }

    }
}