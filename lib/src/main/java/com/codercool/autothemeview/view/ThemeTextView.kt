package com.codercool.autothemeview.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.codercool.autothemeview.helper.ChangeThemeDelegate
import com.codercool.autothemeview.helper.IChangeTheme
import com.codercool.autothemeview.helper.ThemeTextViewHelper

class ThemeTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet?, defStyleAttr: Int = android.R.attr.textViewStyle
) : AppCompatTextView(context, attrs, defStyleAttr), IChangeTheme by ChangeThemeDelegate() {

    private var themeViewHelper = ThemeTextViewHelper(this)

    init {
        themeViewHelper.initParams(context, attrs, defStyleAttr)
        setThemeViewHelper(themeViewHelper)
    }

    override fun setOnClickListener(l: OnClickListener?) {
        super.setOnClickListener(l)
        themeViewHelper.updateClickBGDrawable()
    }

    override fun setOnLongClickListener(l: OnLongClickListener?) {
        super.setOnLongClickListener(l)
        themeViewHelper.updateClickBGDrawable()
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        themeViewHelper.onVisibilityChanged(visibility)
    }
}