package dev.forcetower.podcasts.widget.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dev.forcetower.podcasts.R

class HideBottomViewBottomSheetBehavior : CoordinatorLayout.Behavior<BottomNavigationView> {
    constructor() : super()
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private var height: Int = 0
    private lateinit var behavior: BottomSheetBehavior<View>

    override fun onLayoutChild(
        parent: CoordinatorLayout,
        child: BottomNavigationView,
        layoutDirection: Int
    ): Boolean {
        val paramsCompat = child.layoutParams as MarginLayoutParams
        height = child.measuredHeight + paramsCompat.bottomMargin
        return super.onLayoutChild(parent, child, layoutDirection)
    }

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: BottomNavigationView,
        dependency: View
    ): Boolean {
        if (dependency.id == R.id.mini_player) {
            return true
        }
        return super.layoutDependsOn(parent, child, dependency)
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: BottomNavigationView,
        dependency: View
    ): Boolean {
        if (dependency.id == R.id.mini_player) {
            if (!::behavior.isInitialized) {
                behavior = BottomSheetBehavior.from(dependency)
                behavior.peekHeight = behavior.peekHeight + child.paddingBottom
            }
            val peek = behavior.peekHeight
            val shown = dependency.y / (dependency.height - peek)
            child.translationY = (height * (1 - shown)).coerceIn(0f..height.toFloat())
        }
        return super.onDependentViewChanged(parent, child, dependency)
    }
}