package dev.forcetower.podcasts.widget.navigation

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.ui.AppBarConfiguration
import androidx.transition.TransitionManager
import java.lang.ref.WeakReference

/**
 * This class is a copy from the AndroidX Navigation component
 *
 * It can't be extended because it's package private but it has an annoying set title.
 * This changes this behaviour. Delete when they release a better version of it ;)
 */
class ToolbarOnDestinationChangedListener(
    toolbar: Toolbar,
    configuration: AppBarConfiguration
) : AbstractAppBarOnDestinationChangedListener(toolbar.context, configuration) {
    private val toolbarWeakReference = WeakReference(toolbar)

    override fun setTitle(title: CharSequence?) {
        // No-op
    }

    override fun setNavigationIcon(icon: Drawable?, contentDescription: Int) {
        val toolbar = toolbarWeakReference.get()
        if (toolbar != null) {
            val useTransition = icon == null && toolbar.navigationIcon != null
            toolbar.navigationIcon = icon
            toolbar.setNavigationContentDescription(contentDescription)
            if (useTransition) {
                TransitionManager.beginDelayedTransition(toolbar)
            }
        }
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        val toolbar = toolbarWeakReference.get()
        if (toolbar == null) {
            controller.removeOnDestinationChangedListener(this)
            return
        }
        super.onDestinationChanged(controller, destination, arguments)
    }
}