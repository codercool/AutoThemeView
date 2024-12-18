package com.codercool.autothemeview.helper

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import com.codercool.autothemeview.R
import com.codercool.autothemeview.ThemeViewParams

/**
 * Copyright © 2024/4/21 Hugecore Information Technology (Guangzhou) Co.,Ltd. All rights reserved.
 * author: YHL
 */
class ThemeImageViewHelper(private val view: ImageView) : ThemeViewHelper(view) {

    override fun initParams(
        context: Context, attrs: AttributeSet?, defStyleAttr: Int, defaultParams: ThemeViewParams
    ) {
        val typedArray = context.obtainStyledAttributes(
            attrs,  R.styleable.ThemeImageView, defStyleAttr, 0
        )
        themeViewParams.darkImgSrc = typedArray.getDrawable(R.styleable.ThemeImageView_theme_darkSrc)
        themeViewParams.lightImgSrc = view.drawable
        typedArray.recycle()
    }

    override fun applyThemeColor() {
        super.applyThemeColor()
        applyImageThemeColor()
    }

    private fun applyImageThemeColor() {
        if (currentIsDarkModel && themeViewParams.darkImgSrc != null && themeViewParams.darkImgSrc != view.drawable) {
            view.setImageDrawable(themeViewParams.darkImgSrc)
        } else if(themeViewParams.lightImgSrc != null && themeViewParams.lightImgSrc != view.drawable) {
            view.setImageDrawable(themeViewParams.lightImgSrc)
        }
    }
}