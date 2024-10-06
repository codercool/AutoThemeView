package com.codercool.autothemeview.helper

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.util.AttributeSet
import android.view.View
import com.codercool.autothemeview.AutoThemeManager
import com.codercool.autothemeview.R
import com.codercool.autothemeview.ThemeViewParams
import kotlin.math.min

/**
 * Copyright © 2024/4/6 Hugecore Information Technology (Guangzhou) Co.,Ltd. All rights reserved.
 * author: YHL
 */
open class ThemeViewHelper(private val view: View) {
    protected var currentIsDarkModel = false

    protected val themeViewParams = ThemeViewParams()

    private val noTransparentColor =
        ColorStateList.valueOf(Color.parseColor("#01000000")) // 非全透明的颜色

    open fun getStyleable(): IntArray = R.styleable.ThemeView

    fun initParams(context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0) {
        val typedArray = context.obtainStyledAttributes(
            attrs, getStyleable(), defStyleAttr, 0
        )

        val defaultParams = AutoThemeManager.getDefaultParams()

        themeViewParams.bgLightColor =
            typedArray.getColorStateList(R.styleable.ThemeTextView_theme_backgroundColor)
                ?: defaultParams.bgLightColor

        themeViewParams.bgDarColor =
            typedArray.getColorStateList(R.styleable.ThemeTextView_theme_backgroundDarkColor)
                ?: defaultParams.bgDarColor

        themeViewParams.rippleLightColor =
            typedArray.getColorStateList(R.styleable.ThemeTextView_theme_rippleColor)
                ?: defaultParams.rippleLightColor

        themeViewParams.rippleDarkColor =
            typedArray.getColorStateList(R.styleable.ThemeTextView_theme_rippleDarkColor)
                ?: defaultParams.rippleDarkColor

        themeViewParams.radius = typedArray.getDimensionPixelSize(
            R.styleable.ThemeTextView_theme_radius, defaultParams.radius
        )

        themeViewParams.radiusTopLeft =
            typedArray.getDimensionPixelSize(
                R.styleable.ThemeTextView_theme_radiusTopLeft, defaultParams.radiusTopLeft
            )

        themeViewParams.radiusTopRight =
            typedArray.getDimensionPixelSize(
                R.styleable.ThemeTextView_theme_radiusTopRight, defaultParams.radiusTopRight
            )

        themeViewParams.radiusBottomLeft =
            typedArray.getDimensionPixelSize(
                R.styleable.ThemeTextView_theme_radiusBottomLeft, defaultParams.radiusBottomLeft
            )

        themeViewParams.radiusBottomRight =
            typedArray.getDimensionPixelSize(
                R.styleable.ThemeTextView_theme_radiusBottomRight, defaultParams.radiusBottomRight
            )

        themeViewParams.borderWith =
            typedArray.getDimensionPixelSize(
                R.styleable.ThemeTextView_theme_borderWidth, defaultParams.borderWith
            )

        themeViewParams.borderLightColor =
            typedArray.getColorStateList(R.styleable.ThemeTextView_theme_borderColor)
                ?: defaultParams.borderLightColor

        themeViewParams.borderDarkColor =
            typedArray.getColorStateList(R.styleable.ThemeTextView_theme_borderDarkColor)
                ?: defaultParams.borderDarkColor

        themeViewParams.isRadiusAdjustBounds =
            typedArray.getBoolean(
                R.styleable.ThemeTextView_theme_isRadiusAdjustBounds,
                defaultParams.isRadiusAdjustBounds
            )

        themeViewParams.rippleEnable = typedArray.getBoolean(
            R.styleable.ThemeTextView_theme_rippleEnable, defaultParams.rippleEnable
        )
        initParams(typedArray, defaultParams)
        typedArray.recycle()
        applyThemeColor()
    }

    open fun initParams(typedArray: TypedArray, defaultParams: ThemeViewParams) {}


    open fun applyThemeColor() {
        currentIsDarkModel = AutoThemeManager.isDarkModel()
        applyBGThemeColor()
    }

    private fun applyBGThemeColor() {
        val rippleEnable =
            themeViewParams.rippleEnable && (view.isClickable || view.isLongClickable)
        if (rippleEnable.not() && (themeViewParams.bgLightColor == null || themeViewParams.bgDarColor == null)) return

        val contentDrawable = createContentDrawable()
        view.background =
            if (rippleEnable && themeViewParams.rippleDarkColor != null && themeViewParams.rippleLightColor != null) {
                RippleDrawable(
                    if (currentIsDarkModel) themeViewParams.rippleDarkColor!! else themeViewParams.rippleLightColor!!,
                    contentDrawable,
                    contentDrawable.constantState?.newDrawable()
                )
            } else {
                contentDrawable
            }
    }

    private fun createContentDrawable(): GradientDrawable {
        return object : GradientDrawable() {
            override fun onBoundsChange(r: Rect) {
                super.onBoundsChange(r)
                if (themeViewParams.isRadiusAdjustBounds) {
                    cornerRadius = (min(r.width(), r.height()) / 2).toFloat()
                }
            }
        }.apply {
            var colorList =
                if (currentIsDarkModel) themeViewParams.bgDarColor else themeViewParams.bgLightColor
            // 如果没有设置背景颜色，或背景全透明，设个非全透明的颜色，防止点击无水波纹效果
            if (themeViewParams.rippleEnable && (colorList == null || Color.alpha(colorList.defaultColor) == Color.TRANSPARENT))
                colorList = noTransparentColor
            color = colorList

            if (themeViewParams.radiusTopLeft > 0 || themeViewParams.radiusTopRight > 0 || themeViewParams.radiusBottomLeft > 0 || themeViewParams.radiusBottomRight > 0) {
                cornerRadii = floatArrayOf(
                    themeViewParams.radiusTopLeft.toFloat(),
                    themeViewParams.radiusTopLeft.toFloat(),
                    themeViewParams.radiusTopRight.toFloat(),
                    themeViewParams.radiusTopRight.toFloat(),
                    themeViewParams.radiusBottomLeft.toFloat(),
                    themeViewParams.radiusBottomLeft.toFloat(),
                    themeViewParams.radiusBottomRight.toFloat(),
                    themeViewParams.radiusBottomRight.toFloat(),
                )
                themeViewParams.isRadiusAdjustBounds = false
            } else {
                if (themeViewParams.radius > 0) {
                    cornerRadius = themeViewParams.radius.toFloat()
                    themeViewParams.isRadiusAdjustBounds = false
                }
            }
            setStroke(
                themeViewParams.borderWith,
                if (currentIsDarkModel) themeViewParams.borderDarkColor else themeViewParams.borderLightColor
            )
        }
    }

    /**
     * 设置点击效果的背景（原本空间可能为未开启点击效果）
     * */
    fun updateClickBGDrawable() {
        val currentDrawable = view.background ?: createContentDrawable()
        if (themeViewParams.rippleEnable && themeViewParams.rippleDarkColor != null && themeViewParams.rippleLightColor != null && currentDrawable !is RippleDrawable) {
            view.background = RippleDrawable(
                if (currentIsDarkModel) themeViewParams.rippleDarkColor!! else themeViewParams.rippleLightColor!!,
                currentDrawable,
                currentDrawable.constantState?.newDrawable()
            )
        }
    }

    fun onVisibilityChanged(visibility: Int) {
        if (visibility == View.VISIBLE) {
            if (currentIsDarkModel != AutoThemeManager.isDarkModel()) {
                applyThemeColor()
            }
        }
    }


}