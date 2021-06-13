package com.stackedprogrammer.androiddevelopertest.adapters

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.stackedprogrammer.androiddevelopertest.R
import com.stackedprogrammer.androiddevelopertest.data.Images
import com.stackedprogrammer.androiddevelopertest.data.Price
import java.text.DecimalFormat

@BindingAdapter("imageUrl")
fun bindImageUrl(view: ImageView, images: Images) {


    val shot = images.shots.find {
        it == "in"
    }

    var imageUrl = images.urlTemplate

    val urlReplaceMap = mapOf("{{scheme}}" to "https:", "{{shot}}" to shot, "{{size}}" to "m")

    urlReplaceMap.entries.forEach {
        imageUrl = imageUrl.replace(it.key, it.value!!)
    }

    if (imageUrl.isNotEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

@BindingAdapter("price")
fun bindProductPrice(view: TextView, price: Price) {

    val priceValue: Double = price.amount.toDouble() / price.divisor.toDouble()
    val format = DecimalFormat("#,###.00")

    view.text = "${format.format(priceValue)} ${price.currency}"
}

@BindingAdapter("icon")
fun bindIcon(view: ImageButton, isFavourite: Boolean) {

    view.setImageResource(
        if (isFavourite) R.drawable.ic_baseline_star_24
        else R.drawable.ic_baseline_star_border_24
    )
}

@BindingAdapter("viewVisibility")
fun bindViewVisibility(view: View, visibility: Int) {
    view.visibility = visibility
}