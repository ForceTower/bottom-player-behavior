package dev.forcetower.podcasts.core.ui.transition

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import androidx.transition.TransitionValues
import androidx.transition.Visibility
import android.view.View
import android.view.ViewGroup
import dev.forcetower.podcasts.core.utils.TransitionUtils

class StaggeredDistanceSlide : Visibility() {
    private val spread = 5

    override fun onAppear(
        sceneRoot: ViewGroup,
        view: View,
        startValues: TransitionValues,
        endValues: TransitionValues
    ): Animator {
        val position = endValues.values[PROPNAME_SCREEN_LOCATION] as IntArray
        return createAnimator(view, (sceneRoot.height + position[1] * spread).toFloat(), 0f)
    }

    override fun onDisappear(
        sceneRoot: ViewGroup,
        view: View?,
        startValues: TransitionValues?,
        endValues: TransitionValues?
    ): Animator {
        view ?: return ObjectAnimator.ofInt(0, 1)
        endValues ?: return ObjectAnimator.ofInt(0, 1)
        val position = endValues.values[PROPNAME_SCREEN_LOCATION] as IntArray
        return createAnimator(view, 0f, (sceneRoot.height + position[1] * spread).toFloat())
    }

    private fun createAnimator(
        view: View,
        startTranslationY: Float,
        endTranslationY: Float
    ): Animator {
        view.translationY = startTranslationY
        val ancestralClipping = TransitionUtils.setAncestralClipping(view, false)
        val transition = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, endTranslationY)
        transition.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                TransitionUtils.restoreAncestralClipping(view, ancestralClipping)
            }
        })
        return transition
    }

    companion object {

        private const val PROPNAME_SCREEN_LOCATION = "android:visibility:screenLocation"
    }
}
