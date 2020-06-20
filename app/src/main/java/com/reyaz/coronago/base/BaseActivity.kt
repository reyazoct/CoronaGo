package com.reyaz.coronago.base

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.reyaz.coronago.utils.closeKeyboard

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adjustFontSize()
    }

    fun initError(viewModel: BaseVM) {
        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            finish()
        })
    }

    private fun adjustFontSize() {
        val configuration = resources.configuration
        configuration.fontScale = 1F
        (getSystemService(Context.WINDOW_SERVICE) as? WindowManager)?.apply {
            val metrics = resources.displayMetrics
            defaultDisplay.getMetrics(metrics)
            metrics.scaledDensity = configuration.fontScale * metrics.density
            createConfigurationContext(configuration)
        }
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN) {
            val currentFocusItem = currentFocus
            if (currentFocusItem is EditText) {
                val rect = Rect()
                currentFocusItem.getGlobalVisibleRect(rect)
                if (!rect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    currentFocusItem.clearFocus()
                    currentFocusItem.closeKeyboard(this)
                }

            }
        }
        return super.dispatchTouchEvent(event)
    }
}