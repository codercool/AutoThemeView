package com.codercool.autothemeview

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable

/**
 * Copyright © 2023/8/20 Hugecore Information Technology (Guangzhou) Co.,Ltd. All rights reserved.
 * author: YHL
 */
open class ThemeViewParams {
    var textDarkColor: ColorStateList? = null
    var lightTextColor: ColorStateList? = null

    var textHintDarkColor: ColorStateList? = null
    var textHintLightColor: ColorStateList? = null

    var bgLightColor: ColorStateList? = null
    var bgDarColor: ColorStateList? = null

    var lightImgSrc:Drawable? = null
    var darkImgSrc:Drawable? = null

    var borderWith = 0
    var borderLightColor: ColorStateList? = null
    var borderDarkColor: ColorStateList? = null

    var rippleEnable = false
    var rippleLightColor: ColorStateList? = null
    var rippleDarkColor: ColorStateList? = null

    var radius: Int = 0
    var radiusTopLeft = 0
    var radiusTopRight = 0
    var radiusBottomLeft = 0
    var radiusBottomRight = 0
    var isRadiusAdjustBounds = false
}