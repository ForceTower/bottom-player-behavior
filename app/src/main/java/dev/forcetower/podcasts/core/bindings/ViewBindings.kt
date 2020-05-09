package dev.forcetower.podcasts.core.bindings

import android.view.View
import androidx.databinding.BindingAdapter
import dev.forcetower.podcasts.core.extensions.doOnApplyWindowInsets
import dev.forcetower.podcasts.core.extensions.getPixelsFromDp
import dev.forcetower.podcasts.core.ui.outline.CircularOutlineProvider
import dev.forcetower.podcasts.core.ui.outline.RoundedOutlineProvider

@BindingAdapter("goneIf")
fun goneIf(view: View, gone: Boolean) {
    view.visibility = if (gone) View.GONE else View.VISIBLE
}

@BindingAdapter("invisibleIf")
fun invisibleIf(view: View, gone: Boolean) {
    view.visibility = if (gone) View.INVISIBLE else View.VISIBLE
}

@BindingAdapter("goneUnless")
fun goneUnless(view: View, condition: Boolean) {
    view.visibility = if (condition) View.VISIBLE else View.GONE
}

@BindingAdapter("invisibleUnless")
fun invisibleUnless(view: View, condition: Boolean) {
    view.visibility = if (condition) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("roundedViewRadius")
fun clipToCircle(view: View, radius: Int) {
    view.clipToOutline = true
    view.outlineProvider = RoundedOutlineProvider(view.context.getPixelsFromDp(radius))
}

@BindingAdapter("clipToCircle")
fun clipToCircle(view: View, clip: Boolean) {
    view.clipToOutline = clip
    view.outlineProvider = if (clip) CircularOutlineProvider else null
}

@BindingAdapter(
    "paddingStartSystemWindowInsets",
    "paddingTopSystemWindowInsets",
    "paddingEndSystemWindowInsets",
    "paddingBottomSystemWindowInsets",
    requireAll = false
)
fun applySystemWindows(
    view: View,
    applyLeft: Boolean,
    applyTop: Boolean,
    applyRight: Boolean,
    applyBottom: Boolean
) {
    view.doOnApplyWindowInsets { _, insets, padding ->
        val left = if (applyLeft) insets.systemWindowInsetLeft else 0
        val top = if (applyTop) insets.systemWindowInsetTop else 0
        val right = if (applyRight) insets.systemWindowInsetRight else 0
        val bottom = if (applyBottom) insets.systemWindowInsetBottom else 0

        view.setPadding(
            padding.left + left,
            padding.top + top,
            padding.right + right,
            padding.bottom + bottom
        )
    }
}