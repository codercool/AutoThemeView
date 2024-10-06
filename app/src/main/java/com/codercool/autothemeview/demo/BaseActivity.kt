package com.codercool.autothemeview.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.codercool.autothemeview.AutoThemeManager

/**
 * Copyright © 2024/10/6 Hugecore Information Technology (Guangzhou) Co.,Ltd. All rights reserved.
 * author: YHL
 */
open class BaseActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    override fun onResume() {
        super.onResume()
        updateStatusBarModel()
    }

    protected fun updateStatusBarModel() {
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)
        // 设置状态栏为亮色或暗色模式
        insetsController.run {
            if (isAppearanceLightNavigationBars == AutoThemeManager.isDarkModel()) isAppearanceLightStatusBars = AutoThemeManager.isDarkModel().not()
        }
    }
}