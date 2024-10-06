package com.codercool.autothemeview.demo

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.R
import androidx.appcompat.widget.AppCompatRadioButton
import com.codercool.autothemeview.helper.ThemeTextViewHelper

/**
 * Copyright © 2024/10/6 Hugecore Information Technology (Guangzhou) Co.,Ltd. All rights reserved.
 * author: YHL
 */
class CustomRadioButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet?, defStyleAttr: Int = R.attr.radioButtonStyle
)  : AppCompatRadioButton(context, attrs, defStyleAttr) {

    private var themeViewHelper = ThemeTextViewHelper(this)

    init {
        themeViewHelper.initParams(context, attrs, defStyleAttr)
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