package com.codercool.autothemeview.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.codercool.autothemeview.helper.ThemeViewHelper

/**
 * Copyright © 2024/4/5 Hugecore Information Technology (Guangzhou) Co.,Ltd. All rights reserved.
 * author: YHL
 */
class ThemeRelativeLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0, defStyleRes: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr, defStyleRes) {

    private var themeViewHelper = ThemeViewHelper(this)

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