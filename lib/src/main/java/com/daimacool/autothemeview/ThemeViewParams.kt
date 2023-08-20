package com.daimacool.autothemeview

import android.content.res.ColorStateList

/**
 * Copyright © 2023/8/20 Hugecore Information Technology (Guangzhou) Co.,Ltd. All rights reserved.
 * author: YHL
 */
open class ThemeViewParams {
    var textDarkColor: ColorStateList? = null
    var lightTextColor: ColorStateList? = null

    var bgLightColor: ColorStateList? = null
    var bgDarColor: ColorStateList? = null

    var radius: Int = 0
    var radiusTopLeft = 0
    var radiusTopRight = 0
    var radiusBottomLeft = 0
    var radiusBottomRight = 0
    var isRadiusAdjustBounds = false

    var borderWith = 0
    var borderLightColor: ColorStateList? = null
    var borderDarkColor: ColorStateList? = null

    var rippleLightColor: ColorStateList? = null
    var rippleDarkColor: ColorStateList? = null
    var rippleEnable = false
}