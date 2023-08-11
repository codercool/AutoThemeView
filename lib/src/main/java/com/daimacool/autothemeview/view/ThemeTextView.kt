package com.daimacool.autothemeview.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.View
import com.daimacool.autothemeview.AutoThemeManager
import com.daimacool.autothemeview.R

class ThemeTextView : androidx.appcompat.widget.AppCompatTextView {

    private var currentIsDarkModel = false

    private var textDarkColor: ColorStateList? = null

    private var lightTextColor: ColorStateList? = null

    private var lightColorBackGround: Drawable? = null

    private var darkColorBackGround: Drawable? = null

    private var lightBGColor: ColorStateList? = null

    private var darBGColor: ColorStateList? = null

    private var radius: Int = 0
    private var mRadiusTopLeft = 0
    private var mRadiusTopRight = 0
    private var mRadiusBottomLeft = 0
    private var mRadiusBottomRight = 0

    private var borderWith = 0
    private var borderColor: ColorStateList? = null
    private var borderDarkColor: ColorStateList? = null


    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(
        context,
        attrs,
        android.R.attr.textViewStyle
    )

    constructor(
        context: Context, attrs: AttributeSet?, defStyleAttr: Int
    ) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val typedArray =
            context.theme.obtainStyledAttributes(attrs, R.styleable.ThemeTextView, defStyleAttr, 0)

        textDarkColor = typedArray.getColorStateList(R.styleable.ThemeTextView_theme_textDarkColor)
        lightBGColor = typedArray.getColorStateList(R.styleable.ThemeTextView_theme_backgroundColor)
        darBGColor =
            typedArray.getColorStateList(R.styleable.ThemeTextView_theme_backgroundDarkColor)
        radius = typedArray.getDimensionPixelSize(R.styleable.ThemeTextView_theme_radius, 0)
        mRadiusTopLeft =
            typedArray.getDimensionPixelSize(R.styleable.ThemeTextView_theme_radiusTopLeft, 0)
        mRadiusTopRight =
            typedArray.getDimensionPixelSize(R.styleable.ThemeTextView_theme_radiusTopRight, 0)
        mRadiusBottomLeft =
            typedArray.getDimensionPixelSize(R.styleable.ThemeTextView_theme_radiusBottomLeft, 0)
        mRadiusBottomRight =
            typedArray.getDimensionPixelSize(R.styleable.ThemeTextView_theme_radiusBottomRight, 0)
        borderWith =
            typedArray.getDimensionPixelSize(R.styleable.ThemeTextView_theme_borderWidth, 0)
        borderColor =
            typedArray.getColorStateList(R.styleable.ThemeTextView_theme_borderColor)
        borderDarkColor =
            typedArray.getColorStateList(R.styleable.ThemeTextView_theme_borderDarkColor)
        lightTextColor = textColors
        applyTextThemeColor()
    }

    override fun setTextAppearance(resId: Int) {
        super.setTextAppearance(resId)
        applyTextThemeColor()
    }

    private fun applyTextThemeColor() {
        currentIsDarkModel = AutoThemeManager.isDarkModel()

        if (currentIsDarkModel && textDarkColor != null && textDarkColor != textColors) {
            setTextColor(textDarkColor)
        } else if (lightTextColor != null && lightTextColor != textColors) {
            setTextColor(lightTextColor)
        }

        background = GradientDrawable().apply {
            color = if (currentIsDarkModel) darBGColor else lightBGColor
            if (mRadiusTopLeft > 0 || mRadiusTopRight > 0 || mRadiusBottomLeft > 0 || mRadiusBottomRight > 0) {
                cornerRadii = floatArrayOf(
                    mRadiusTopLeft.toFloat(), mRadiusTopLeft.toFloat(),
                    mRadiusTopRight.toFloat(), mRadiusTopRight.toFloat(),
                    mRadiusBottomLeft.toFloat(), mRadiusBottomLeft.toFloat(),
                    mRadiusBottomRight.toFloat(), mRadiusBottomRight.toFloat(),
                )
            } else {
                cornerRadius = radius.toFloat()
            }
            setStroke(borderWith, if (currentIsDarkModel) borderDarkColor else borderColor)
        }
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        if (visibility == View.VISIBLE) {
            if (currentIsDarkModel != AutoThemeManager.isDarkModel()) {
                applyTextThemeColor()
            }
        }
    }
}