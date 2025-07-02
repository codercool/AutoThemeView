package com.codercool.autothemeview.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import com.codercool.autothemeview.helper.ChangeThemeDelegate
import com.codercool.autothemeview.helper.IChangeTheme
import com.codercool.autothemeview.helper.ThemeTextViewHelper

/**
 * Copyright © 2024/9/8 Hugecore Information Technology (Guangzhou) Co.,Ltd. All rights reserved.
 * author: YHL
 */
class ThemeEditText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet?, defStyleAttr: Int = android.R.attr.editTextStyle
) : AppCompatEditText(context, attrs, defStyleAttr), IChangeTheme by ChangeThemeDelegate() {

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