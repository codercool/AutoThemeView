package com.codercool.autothemeview.helper

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import com.codercool.autothemeview.R
import com.codercool.autothemeview.ThemeViewParams

/**
 * Copyright © 2024/4/21 Hugecore Information Technology (Guangzhou) Co.,Ltd. All rights reserved.
 * author: YHL
 */
class ThemeTextViewHelper(private val view: TextView) : ThemeViewHelper(view) {

    override fun initParams(
        context: Context, attrs: AttributeSet?, defStyleAttr: Int, defaultParams: ThemeViewParams
    ) {
        val typedArray = context.obtainStyledAttributes(
            attrs, R.styleable.ThemeTextView, defStyleAttr, 0
        )
        themeViewParams.textDarkColor =
            typedArray.getColorStateList(R.styleable.ThemeTextView_theme_textDarkColor)
                ?: defaultParams.textDarkColor
        themeViewParams.lightTextColor = view.textColors

        themeViewParams.textHintDarkColor =
            typedArray.getColorStateList(R.styleable.ThemeTextView_theme_textHintDarkColor)
                ?: defaultParams.textHintDarkColor

        themeViewParams.textHintLightColor = view.hintTextColors
        typedArray.recycle()
    }

    override fun applyThemeColor() {
        super.applyThemeColor()
        applyTextThemeColor()
    }

    private fun applyTextThemeColor() {
        if (currentIsDarkModel) {
            if (themeViewParams.textDarkColor != null && themeViewParams.textDarkColor != view.textColors)
                view.setTextColor(themeViewParams.textDarkColor)
            if (themeViewParams.textHintDarkColor != null && themeViewParams.textHintDarkColor != view.hintTextColors)
                view.setHintTextColor(themeViewParams.textHintDarkColor)
        } else {
            if (themeViewParams.lightTextColor != null && themeViewParams.lightTextColor != view.textColors)
                view.setTextColor(themeViewParams.lightTextColor)
            if (themeViewParams.textHintLightColor != null && themeViewParams.textHintLightColor != view.hintTextColors)
                view.setHintTextColor(themeViewParams.textHintLightColor)
        }
    }
}