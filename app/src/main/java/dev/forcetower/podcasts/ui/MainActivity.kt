package dev.forcetower.podcasts.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import dev.forcetower.podcasts.R
import dev.forcetower.podcasts.core.base.BaseActivity
import dev.forcetower.podcasts.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.rootView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
    }

    private fun getNavController() = findNavController(R.id.fragment_container)

    override fun onSupportNavigateUp() = getNavController().navigateUp()

    override fun getSnackInstance(string: String, duration: Int): Snackbar? {
        return Snackbar.make(binding.rootView, string, duration).apply {
            anchorView = binding.bottomNav
        }
    }
}
