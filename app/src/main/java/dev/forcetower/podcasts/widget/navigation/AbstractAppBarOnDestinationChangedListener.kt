package dev.forcetower.podcasts.widget.navigation

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import androidx.navigation.FloatingWindow
import androidx.navigation.NavController
import androidx.navigation.NavController.OnDestinationChangedListener
import androidx.navigation.NavDestination
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.R
import java.lang.ref.WeakReference
import java.util.regex.Pattern

abstract class AbstractAppBarOnDestinationChangedListener(
    private val context: Context,
    configuration: AppBarConfiguration
) : OnDestinationChangedListener {
    private val topLevelDestinations = configuration.topLevelDestinations
    private val drawerLayoutWeakReference = configuration.drawerLayout?.let { WeakReference(it) }
    private lateinit var arrowDrawable: DrawerArrowDrawable
    private lateinit var animator: Animator

    protected abstract fun setTitle(title: CharSequence?)

    protected abstract fun setNavigationIcon(icon: Drawable?, @StringRes contentDescription: Int)

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        if (destination is FloatingWindow) {
            return
        }
        val drawerLayout = drawerLayoutWeakReference?.get()

        if (drawerLayoutWeakReference != null && drawerLayout == null) {
            controller.removeOnDestinationChangedListener(this)
            return
        }

        val label = destination.label
        if (label != null) {
            val title = StringBuffer()
            val fillInPattern =
                Pattern.compile("\\{(.+?)}")
            val matcher = fillInPattern.matcher(label)
            while (matcher.find()) {
                val argName = matcher.group(1)
                if (arguments != null && arguments.containsKey(argName)) {
                    matcher.appendReplacement(title, "")
                    title.append(arguments[argName].toString())
                } else {
                    throw IllegalArgumentException(
                        "Could not find " + argName + " in " +
                                arguments + " to fill label " + label
                    )
                }
            }
            matcher.appendTail(title)
            setTitle(title)
        }

        val isTopLevelDestination =
            matchDestinations(
                destination,
                topLevelDestinations
            )
        if (drawerLayout == null && isTopLevelDestination) {
            setNavigationIcon(null, 0)
        } else {
            setActionBarUpIndicator(drawerLayout != null && isTopLevelDestination)
        }
    }

    private fun setActionBarUpIndicator(showAsDrawerIndicator: Boolean) {
        var animate = true
        if (!::arrowDrawable.isInitialized) {
            arrowDrawable = DrawerArrowDrawable(context)
            animate = false
        }
        setNavigationIcon(
            arrowDrawable,
            if (showAsDrawerIndicator) R.string.nav_app_bar_open_drawer_description else R.string.nav_app_bar_navigate_up_description
        )
        val endValue = if (showAsDrawerIndicator) 0f else 1f
        if (animate) {
            val startValue: Float = arrowDrawable.progress
            if (!::animator.isInitialized) {
                animator.cancel()
            }
            animator = ObjectAnimator.ofFloat(
                arrowDrawable, "progress",
                startValue, endValue
            )
            animator.start()
        } else {
            arrowDrawable.progress = endValue
        }
    }

    companion object {
        fun matchDestinations(
            destination: NavDestination,
            destinationIds: Set<Int?>
        ): Boolean {
            var currentDestination: NavDestination? = destination
            do {
                if (destinationIds.contains(currentDestination?.id)) {
                    return true
                }
                currentDestination = currentDestination?.parent
            } while (currentDestination != null)
            return false
        }
    }
}