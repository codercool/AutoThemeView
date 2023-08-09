package com.daimacool.autothemeview.view

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import com.daimacool.autothemeview.R

class ThemeTextView : androidx.appcompat.widget.AppCompatTextView {

    private var textNightColor: ColorStateList? = null

    private var lightTextColor: ColorStateList? = null

    private var initialized = false

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(
        context,
        attrs,
        android.R.attr.textViewStyle
    )

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val typedArray =
            context.theme.obtainStyledAttributes(attrs, R.styleable.ThemeTextView, defStyleAttr, 0)
        for (i in 0 until typedArray.indexCount) {
            val index = typedArray.getIndex(i)
            if (index == R.styleable.ThemeTextView_theme_textDarkColor) {
                textNightColor = typedArray.getColorStateList(index)
            }
        }
        initialized = true
        applyTextThemeColor()
    }

    override fun setTextAppearance(resId: Int) {
        super.setTextAppearance(resId)
        applyTextThemeColor()
    }

    override fun setTextColor(color: Int) {
        if (initialized.not()) {
            lightTextColor = ColorStateList.valueOf(color)
            return
        }
        super.setTextColor(color)
    }

    override fun setTextColor(colors: ColorStateList?) {
        if (initialized.not()) {
            lightTextColor = colors
            return
        }
        super.setTextColor(colors)
    }

    private fun applyTextThemeColor() {
        // todo aa
        val isDark = true
        if (isDark && textNightColor != null) {
            setTextColor(textNightColor)
        } else {
            lightTextColor?.let {
                setTextColor(it)
            }
        }
    }
}