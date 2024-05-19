package com.codercool.autothemeview.helper

import android.content.res.TypedArray
import android.widget.TextView
import com.codercool.autothemeview.R
import com.codercool.autothemeview.ThemeViewParams

/**
 * Copyright © 2024/4/21 Hugecore Information Technology (Guangzhou) Co.,Ltd. All rights reserved.
 * author: YHL
 */
class ThemeTextViewHelper(private val view: TextView) : ThemeViewHelper(view) {

    override fun getStyleable(): IntArray = R.styleable.ThemeTextView

    override fun initParams(typedArray: TypedArray,defaultParams:ThemeViewParams) {
        themeViewParams.textDarkColor = typedArray.getColorStateList(R.styleable.ThemeTextView_theme_textDarkColor)
            ?: defaultParams.textDarkColor

        themeViewParams.lightTextColor = view.textColors
    }

    override fun applyThemeColor() {
        super.applyThemeColor()
        applyTextThemeColor()
    }

    private fun applyTextThemeColor() {
        if (currentIsDarkModel && themeViewParams.textDarkColor != null && themeViewParams.textDarkColor != view.textColors) {
            view.setTextColor(themeViewParams.textDarkColor)
        } else if (themeViewParams.lightTextColor != null && themeViewParams.lightTextColor != view.textColors) {
            view.setTextColor(themeViewParams.lightTextColor)
        }
    }
}