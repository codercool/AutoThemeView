package com.daimacool.autothemeview

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams

/**
 *  Copyright © 2023/8/10 Hugecore Information Technology (Guangzhou) Co.,Ltd. All rights reserved.
 *  author: YHL
 */
class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        findViewById<View>(R.id.tv_normal).setOnLongClickListener {
            findViewById<View>(R.id.ttv_test).setOnClickListener {
                AutoThemeManager.setDarkModel(AutoThemeManager.isDarkModel().not())
                /*it.rootView.isVisible = false
                it.rootView.isVisible = true*/
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