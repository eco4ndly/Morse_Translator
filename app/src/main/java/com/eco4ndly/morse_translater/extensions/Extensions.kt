package com.eco4ndly.morse_translater.extensions

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.lang.Exception

/**
 * A Sayan Porya code on 28/04/20
 */

/**
 * Sets on click listener to a view
 */
@ExperimentalCoroutinesApi
fun View.clicks(): Flow<Unit> = callbackFlow {
    val listener = View.OnClickListener { safeOffer(Unit) }
    setOnClickListener(listener)
    awaitClose {
        setOnClickListener(null)
    }
}

/**
 * Adds a text watcher i.e. text change listener to a edittext
 */
@ExperimentalCoroutinesApi
fun EditText.textChanges() = callbackFlow<String> {
    addTextChangedListener(object :TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            p0?.let {
                safeOffer(it.toString())
            }
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            //There is no requirement of implementation
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            //There is no requirement of implementation
        }

    })

    awaitClose {
        addTextChangedListener(null)
    }
}

@ExperimentalCoroutinesApi
private fun <E> SendChannel<E>.safeOffer(value: E) = !isClosedForSend && try {
    offer(value)
} catch (t: Throwable) {
    // Ignore all
    false
}

/**
 * To add FLOW click listener on multiple views at once.
 * Rather having code like this
 * @code
 *    view1.clicks()
        .onEach {
          //View 1 Stuffs
        }
        .launchIn(mainScope)

      view2.clicks()
        .onEach {
          //View 1 Stuffs
        }
        .launchIn(mainScope)

      view3.clicks()
        .onEach {
          //View 1 Stuffs
      }
      .launchIn(mainScope)
 **************************
 * Having this |
 * @code
 *    clicks(view1, view2, view3, mainScope) { clickedViewId ->
        when(clickedViewId) {
          R.id.view1 -> {}
          R.id.view2 -> {}
          R.id.view3 -> {}
        }
 *    }
 *******************************
 *
 * The code becomes much concise
 *
 * Has dependency on Flow clicks() method
 * @link https://github.com/eco4ndly/useful_kotlin_extension_functions/blob/master/CoroutineExtensions.kt
 */
fun Any.clicks(vararg views: View, mainScope: CoroutineScope, onClick: (viewId: Int) -> Unit) {

    views.forEach {view ->
        view.clicks() // Flow Clicks method
            .onEach {
                onClick.invoke(view.id)
            }.launchIn(mainScope)
    }

}


/**
 * This extension property will be used in when statements to exhaust all of its branches.
 *
 * This property has a custom getter which returns the object itself,
 * so if we use it on a when block, it’s treated as an expression and the compiler will
 * force us to specify all cases.
 *
 * https://proandroiddev.com/til-when-is-when-exhaustive-31d69f630a8b
 */
val <T> T.exhaustive: T
  get() = this

/**
 * Launches web browser if the string qualifies as a web url
 */
fun String.launchBrowserIfUrl(context: Context) {
  try {
    Intent(Intent.ACTION_VIEW, Uri.parse(this)).apply {
      addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      context.startActivity(this)
    }
  } catch (e: ActivityNotFoundException) {
    e.printStackTrace()
  }
}