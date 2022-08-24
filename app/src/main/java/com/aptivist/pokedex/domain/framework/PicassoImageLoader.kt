package com.aptivist.pokedex.domain.framework

import android.view.View
import android.widget.ImageView
import com.aptivist.pokedex.domain.IImageLoader
import com.squareup.picasso.Picasso
import javax.inject.Inject

class PicassoImageLoader @Inject constructor(private val picasso: Picasso) : IImageLoader {
    override fun loadImage(url: String, view: ImageView) {
        picasso.load(url).into(view)
    }
}