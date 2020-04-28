package com.eco4ndly.morse_translater.about

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.eco4ndly.morse_translater.R
import com.eco4ndly.morse_translater.extensions.clicks
import com.eco4ndly.morse_translater.extensions.launchBrowserIfUrl
import kotlinx.android.synthetic.main.layout_about.iv_fb_about
import kotlinx.android.synthetic.main.layout_about.iv_github_about
import kotlinx.android.synthetic.main.layout_about.iv_link_about
import kotlinx.android.synthetic.main.layout_about.iv_linkedin_about
import kotlinx.android.synthetic.main.layout_about.view.iv_fb_about
import kotlinx.android.synthetic.main.layout_about.view.iv_github_about
import kotlinx.android.synthetic.main.layout_about.view.iv_link_about
import kotlinx.android.synthetic.main.layout_about.view.iv_linkedin_about
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import java.util.jar.Attributes

/**
 * A Sayan Porya code on 28/04/20
 */
class AboutView(context: Context, attributes: AttributeSet): FrameLayout(context, attributes) {
  private var viewScope: CoroutineScope? = null
  override fun onFinishInflate() {
    super.onFinishInflate()
    if (viewScope == null) {
      viewScope = MainScope()
    }
    clicks(
        iv_link_about,
        iv_github_about,
        iv_linkedin_about,
        iv_fb_about,
        mainScope = viewScope!!
    ) { viewClicked ->
      val link = when (viewClicked) {
        R.id.iv_link_about -> "http://www.sayanporya.com"
        R.id.iv_github_about -> "https://github.com/eco4ndly/"
        R.id.iv_linkedin_about -> "https://www.linkedin.com/in/sayan-porya-240031159/"
        R.id.iv_fb_about -> "https://www.facebook.com/skpsayan"
        else -> "https://play.google.com/store/apps/details?id=com.eco4ndly.morse_translater&hl=en"
      }

      link.launchBrowserIfUrl(context)
    }
  }

  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    viewScope?.cancel()
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    if (viewScope == null) {
      viewScope = MainScope()
    }
  }

}