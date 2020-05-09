package dev.forcetower.podcasts.ui.miniplayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dev.forcetower.podcasts.core.base.BaseFragment
import dev.forcetower.podcasts.databinding.FragmentMiniPlayerBinding

class MiniPlayerFragment : BaseFragment() {
    private lateinit var behavior: BottomSheetBehavior<MotionLayout>
    private lateinit var binding: FragmentMiniPlayerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentMiniPlayerBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        behavior = BottomSheetBehavior.from(binding.contentRoot)
        behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                binding.contentRoot.progress = slideOffset
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) = Unit
        })
    }
}