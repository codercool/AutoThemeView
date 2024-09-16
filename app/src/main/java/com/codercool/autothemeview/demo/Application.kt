package com.codercool.autothemeview.demo

import android.app.Application
import com.codercool.autothemeview.AutoThemeManager

/**
 *  Copyright © 2023/8/15 Hugecore Information Technology (Guangzhou) Co.,Ltd. All rights reserved.
 *  author: YHL
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val defaultParams = AutoThemeManager.Builder(this)
            .setTextDarkColor(R.color.white)
            /*.setBGColor(R.color.teal_700, R.color.color_blue_bg_normal)
            .setBorderColor(R.color.purple_200, R.color.purple_200)
            .setBorderWidth(3f)*/
            .setIsRadiusAdjustBounds(true)
            /*.setRadius(40f)
            .setRadiusBottomLeft(20f)
            .setRadiusBottomRight(30f)*/
            .setRippleColor(R.color.purple_700, R.color.teal_200)
            .setRippleEnable(true)
            .build()
        AutoThemeManager.setDefaultParams(defaultParams)
    }
}