package dev.forcetower.podcasts.core.bindings

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import dev.forcetower.podcasts.R
import dev.forcetower.podcasts.core.utils.ViewUtils

@BindingAdapter(value = [
    "imageUrl",
    "imageUri",
    "clipCircle",
    "listener",
    "dontTransform"
], requireAll = false)
fun imageUrl(
    imageView: ImageView,
    imageUrl: String?,
    imageUri: Uri?,
    clipCircle: Boolean? = false,
    listener: ImageLoadListener? = null,
    dontTransform: Boolean? = false
) {
    val load = imageUrl ?: imageUri ?: return
    val context = imageView.context
    var request = Glide.with(imageView)
        .load(load)
        .placeholder(ColorDrawable(ViewUtils.attributeColorUtils(context, R.attr.placeholderColor)))
    if (clipCircle == true) request = request.circleCrop()
    if (dontTransform == true) request = request.dontTransform()

    if (listener != null) {
        request = request.listener(object : RequestListener<Drawable> {
            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                listener.onImageLoaded()
                return false
            }

            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                listener.onImageLoadFailed()
                return false
            }
        })
    }
    request.into(imageView)
}

interface ImageLoadListener {
    fun onImageLoaded()
    fun onImageLoadFailed()
}