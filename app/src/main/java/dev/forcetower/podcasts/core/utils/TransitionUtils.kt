package dev.forcetower.podcasts.core.utils

import android.view.View
import android.view.ViewGroup

object TransitionUtils {
    fun setAncestralClipping(view: View, clipChildren: Boolean): MutableList<Boolean> {
        return setAncestralClipping(view, clipChildren, mutableListOf())
    }

    private fun setAncestralClipping(
        view: View,
        clipChildren: Boolean,
        was: MutableList<Boolean>
    ): MutableList<Boolean> {
        if (view is ViewGroup) {
            was.add(view.clipChildren)
            view.clipChildren = clipChildren
        }
        val parent = view.parent
        if (parent != null && parent is ViewGroup) {
            setAncestralClipping(parent, clipChildren, was)
        }
        return was
    }

    fun restoreAncestralClipping(view: View, was: MutableList<Boolean>) {
        if (view is ViewGroup) {
            view.clipChildren = was.removeAt(0)
        }
        val parent = view.parent
        if (parent is ViewGroup) {
            restoreAncestralClipping(parent, was)
        }
    }
}