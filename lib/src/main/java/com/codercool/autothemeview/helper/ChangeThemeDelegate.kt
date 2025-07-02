package com.codercool.autothemeview.helper

import android.content.res.ColorStateList

/**
 *  Copyright © 2025/7/2 Hugecore Information Technology (Guangzhou) Co.,Ltd. All rights reserved.
 *  author: YHL
 */
class ChangeThemeDelegate : IChangeTheme {

    private var themeViewHelper:ThemeViewHelper? = null

    override fun setRadius(radius: Int) {
        themeViewHelper?.run {
            themeViewParams.run {
                this.radius = radius
                radiusTopLeft = 0
                radiusTopRight = 0
                radiusBottomLeft = 0
                radiusBottomRight = 0
            }
            applyBGThemeColor()
        }
    }

    override fun setRadius(
        topLeft: Int, topRight: Int, bottomLeft: Int, bottomRight: Int
    ) {
        themeViewHelper?.run {
            themeViewParams.run {
                this.radius = 0
                this.radiusTopLeft = topLeft
                this.radiusTopRight = topRight
                this.radiusBottomLeft = bottomLeft
                this.radiusBottomRight = bottomRight
            }
            applyBGThemeColor()
        }
    }

    override fun setBackGroundColor(lightColor: ColorStateList, darkColor: ColorStateList?) {
        themeViewHelper?.run {
            themeViewParams.run {
                bgLightColor = lightColor
                bgDarColor = darkColor
            }
            applyBGThemeColor()
        }
    }

    override fun setThemeViewHelper(themeViewHelper:ThemeViewHelper) {
        this.themeViewHelper = themeViewHelper
    }
}

interface IChangeTheme {

    fun setRadius(radius: Int)

    fun setRadius(
        topLeft: Int = 0, topRight: Int = 0, bottomLeft: Int = 0, bottomRight: Int = 0
    )

    fun setBackGroundColor(lightColor: ColorStateList, darkColor: ColorStateList? = null)

    fun setThemeViewHelper(themeViewHelper:ThemeViewHelper)
}