package com.codercool.autothemeview.demo

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import com.codercool.autothemeview.AutoThemeManager

/**
 *  Copyright © 2023/8/10 Hugecore Information Technology (Guangzhou) Co.,Ltd. All rights reserved.
 *  author: YHL
 */
class ThemeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)
        // 设置状态栏为亮色模式，字体变为深色
        insetsController.isAppearanceLightStatusBars = true

        setContentView(R.layout.activity_theme)

        findViewById<TextView>(R.id.tv_title).text = "主题页面"

        findViewById<View>(R.id.tv_normal).setOnLongClickListener {
            findViewById<View>(R.id.ttv_test).setOnClickListener {
                AutoThemeManager.setDarkModel(AutoThemeManager.isDarkModel().not())
                it.rootView.isVisible = false
                it.rootView.isVisible = true
            }
            return@setOnLongClickListener true
        }

        findViewById<View>(R.id.tv_visble).setOnClickListener {
            findViewById<View>(R.id.ttv_test).updateLayoutParams<ConstraintLayout.LayoutParams> {
                height = 150
            }
        }
    }
}