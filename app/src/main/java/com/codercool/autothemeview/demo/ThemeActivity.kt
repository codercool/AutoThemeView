package com.codercool.autothemeview.demo

import android.os.Bundle
import android.widget.ImageView
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.codercool.autothemeview.AutoThemeManager
import com.codercool.autothemeview.view.ThemeImageView
import com.codercool.autothemeview.view.ThemeLinearlayout
import com.codercool.autothemeview.view.ThemeTextView

/**
 *  Copyright © 2023/8/10 Hugecore Information Technology (Guangzhou) Co.,Ltd. All rights reserved.
 *  author: YHL
 */
class ThemeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_theme)

        findViewById<ThemeTextView>(R.id.tv_title).text = "主题页面"

        findViewById<ImageView>(R.id.iv_back).setOnClickListener {
            finish()
        }

        findViewById<RadioGroup>(R.id.rg_theme_state).run {
            check(if (AutoThemeManager.isDarkModel()) R.id.rb_theme_state_dark else R.id.rb_theme_state_light)
            setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.rb_theme_state_light -> {
                        changeTheme(false)
                    }

                    R.id.rb_theme_state_dark -> {
                        changeTheme(true)
                    }
                }
            }
        }

        // 设置点击事件后，点击才会有水波纹效果
        findViewById<ThemeTextView>(R.id.ttv_text).setOnClickListener {}
        findViewById<ThemeLinearlayout>(R.id.ll_linearlayout).setOnClickListener {}
        findViewById<ThemeImageView>(R.id.iv_image).setOnClickListener {}

    }

    private fun changeTheme(isDark:Boolean) {
        AutoThemeManager.setDarkModel(isDark)
        AutoThemeManager.refreshCurrentPageTheme(this)
        updateStatusBarModel()
    }
}