package com.codecool.autothemeview

import android.app.Application

/**
 *  Copyright © 2023/8/15 Hugecore Information Technology (Guangzhou) Co.,Ltd. All rights reserved.
 *  author: YHL
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val params = AutoThemeManager.Builder(this)
            .setTextDarkColor(R.color.white)
            .setBGLightColor(R.color.teal_700)
            .setBGDarkColor(R.color.color_blue_bg_normal)
            .setBorderLightColor(R.color.purple_200)
            .setBorderDarkColor(R.color.purple_500)
            .setBorderWidth(3f)
            .setIsRadiusAdjustBounds(true)
            /*.setRadius(40f)
            .setRadiusBottomLeft(20f)
            .setRadiusBottomRight(30f)*/
            .setRippleLightColor(R.color.purple_700)
            .setRippleDarkColorColor(R.color.teal_200)
            .setRippleEnable(true)
            .build()
        AutoThemeManager.setDefaultParams(params)
    }
}