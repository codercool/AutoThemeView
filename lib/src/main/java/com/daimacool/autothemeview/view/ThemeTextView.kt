package com.daimacool.autothemeview.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.daimacool.autothemeview.AutoThemeManager
import com.daimacool.autothemeview.R

class ThemeTextView : androidx.appcompat.widget.AppCompatTextView {

    private var textDarkColor: ColorStateList? = null

    private var lightTextColor: ColorStateList? = null

    private var lightColorBackGround:Drawable? = null

    private var darkColorBackGround:Drawable? = null

    private var lightBGColor:ColorStateList? = null

    private var darBGColor:ColorStateList? = null

    private var currentIsDarkModel = false

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(
        context,
        attrs,
        android.R.attr.textViewStyle
    )

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int
    ) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val typedArray =
            context.theme.obtainStyledAttributes(attrs, R.styleable.ThemeTextView, defStyleAttr, 0)
        for (i in 0 until typedArray.indexCount) {
            when(val index = typedArray.getIndex(i)) {
                R.styleable.ThemeTextView_theme_textDarkColor -> {
                    textDarkColor = typedArray.getColorStateList(index)
                }
                R.styleable.ThemeTextView_theme_backgroundColor -> {
                    lightBGColor = typedArray.getColorStateList(index)
                }
                R.styleable.ThemeTextView_theme_backgroundDarkColor -> {
                    darBGColor = typedArray.getColorStateList(index)
                }
            }
        }
        lightTextColor = textColors
        if (currentIsDarkModel != AutoThemeManager.isDarkModel()) {
            applyTextThemeColor()
        }
    }

    override fun setTextAppearance(resId: Int) {
        super.setTextAppearance(resId)
        applyTextThemeColor()
    }

    private fun applyTextThemeColor() {
        currentIsDarkModel = AutoThemeManager.isDarkModel()
        if (AutoThemeManager.isDarkModel() && textDarkColor != null && textDarkColor != textColors) {
            setTextColor(textDarkColor)
        } else if (lightTextColor != null && lightTextColor != textColors){
            setTextColor(lightTextColor)
        }

        background = GradientDrawable().apply {
            color = if (currentIsDarkModel) darBGColor else lightBGColor
            cornerRadius = 10f
        }
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        if (visibility == View.VISIBLE) {
            if (currentIsDarkModel != AutoThemeManager.isDarkModel()) {
                applyTextThemeColor()
            }
        }
        Log.i("---","---:onVisibilityChanged:"+changedView+"_"+visibility)
    }
}