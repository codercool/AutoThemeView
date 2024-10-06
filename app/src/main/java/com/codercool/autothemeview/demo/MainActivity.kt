package com.codercool.autothemeview.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<ImageView>(R.id.iv_back).isVisible = false
        findViewById<TextView>(R.id.tv_title).text = "主页"

        findViewById<TextView>(R.id.tv_go_theme_page).setOnClickListener {
            startActivity(Intent(this, ThemeActivity::class.java))
        }
    }
}