package com.daimacool.autothemeview

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

/**
 *  Copyright © 2023/8/10 Hugecore Information Technology (Guangzhou) Co.,Ltd. All rights reserved.
 *  author: YHL
 */
class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        findViewById<View>(R.id.ttv_test).setOnClickListener {
            AutoThemeManager.setDarkModel(true)
        }

        findViewById<View>(R.id.tv_visble).setOnClickListener {
            it.isVisible = false
        }
    }
}