package com.stackedprogrammer.androiddevelopertest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.stackedprogrammer.androiddevelopertest.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_listing_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ProductListingFragment.newInstance())
                .commitNow()
        }
    }
}