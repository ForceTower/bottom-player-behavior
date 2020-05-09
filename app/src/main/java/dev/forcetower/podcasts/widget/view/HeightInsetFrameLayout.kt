package dev.forcetower.podcasts.widget.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import dev.forcetower.podcasts.R
import timber.log.Timber

class HeightInsetFrameLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {
    private val bottomInset: Boolean
    private val topInset: Boolean
    private var finalHeight: Int
    private val requestedHeight: Int

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.HeightInsetFrameLayout, defStyleAttr, defStyleRes)
        try {
            requestedHeight = a.getDimension(R.styleable.HeightInsetFrameLayout_android_layout_height, 0f).toInt() + paddingBottom + paddingTop
            bottomInset = a.getBoolean(R.styleable.HeightInsetFrameLayout_bottomInset, false)
            topInset = a.getBoolean(R.styleable.HeightInsetFrameLayout_topInset, false)
            Timber.d("Requested height: $requestedHeight")
            finalHeight = requestedHeight

            setOnApplyWindowInsetsListener { _, insets ->
                val bot = if (bottomInset) insets.systemWindowInsetBottom else 0
                val top = if (topInset) insets.systemWindowInsetTop else 0
                val sum = bot + top
                finalHeight = (requestedHeight + sum)
                Timber.d("Listener... $requestedHeight $finalHeight")
                requestLayout()
                insets
            }
        } finally {
            a.recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val specMode = MeasureSpec.getMode(heightMeasureSpec)
        super.onMeasure(widthMeasureSpec, specMode or finalHeight)
    }
}