package dev.forcetower.podcasts.core.utils

import android.content.Context
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt

object ViewUtils {
    @JvmStatic
    @ColorInt
    fun attributeColorUtils(context: Context, @AttrRes attribute: Int): Int {
        val typedValue = context.obtainStyledAttributes(intArrayOf(attribute))
        val color = typedValue.getColor(0, 0)
        typedValue.recycle()
        return color
    }
}