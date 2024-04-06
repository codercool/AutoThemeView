package com.daimacool.autothemeview.view

import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import com.daimacool.autothemeview.AutoThemeManager
import com.daimacool.autothemeview.R
import com.daimacool.autothemeview.ThemeViewParams
import kotlin.math.min

/**
 * Copyright © 2024/4/6 Hugecore Information Technology (Guangzhou) Co.,Ltd. All rights reserved.
 * author: YHL
 */
class ThemeViewHelper(private val view:View) {
    private var currentIsDarkModel = false

    private val themeViewParams = ThemeViewParams()

    fun initParams(context:Context, attrs: AttributeSet?, defStyleAttr: Int = 0) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ThemeTextView, defStyleAttr, 0)

        val defaultParams = AutoThemeManager.getDefaultParams()

        themeViewParams.textDarkColor = typedArray.getColorStateList(R.styleable.ThemeTextView_theme_textDarkColor)
            ?: defaultParams.textDarkColor

        themeViewParams.bgLightColor = typedArray.getColorStateList(R.styleable.ThemeTextView_theme_backgroundColor)
            ?: defaultParams.bgLightColor

        themeViewParams.bgDarColor =
            typedArray.getColorStateList(R.styleable.ThemeTextView_theme_backgroundDarkColor)
                ?: defaultParams.bgDarColor

        themeViewParams.rippleLightColor = typedArray.getColorStateList(R.styleable.ThemeTextView_theme_rippleColor)
            ?: defaultParams.rippleLightColor

        themeViewParams.rippleDarkColor =
            typedArray.getColorStateList(R.styleable.ThemeTextView_theme_rippleDarkColor)
                ?: defaultParams.rippleDarkColor

        themeViewParams.radius = typedArray.getDimensionPixelSize(
            R.styleable.ThemeTextView_theme_radius,
            defaultParams.radius
        )

        themeViewParams.radiusTopLeft =
            typedArray.getDimensionPixelSize(
                R.styleable.ThemeTextView_theme_radiusTopLeft,
                defaultParams.radiusTopLeft
            )

        themeViewParams.radiusTopRight =
            typedArray.getDimensionPixelSize(
                R.styleable.ThemeTextView_theme_radiusTopRight,
                defaultParams.radiusTopRight
            )

        themeViewParams.radiusBottomLeft =
            typedArray.getDimensionPixelSize(
                R.styleable.ThemeTextView_theme_radiusBottomLeft,
                defaultParams.radiusBottomLeft
            )

        themeViewParams.radiusBottomRight =
            typedArray.getDimensionPixelSize(
                R.styleable.ThemeTextView_theme_radiusBottomRight,
                defaultParams.radiusBottomRight
            )

        themeViewParams.borderWith =
            typedArray.getDimensionPixelSize(
                R.styleable.ThemeTextView_theme_borderWidth,
                defaultParams.borderWith
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
            R.styleable.ThemeTextView_theme_rippleEnable,
            defaultParams.rippleEnable
        )
        typedArray.recycle()
        if (view is TextView) themeViewParams.lightTextColor = view.textColors
        applyThemeColor()
    }


    private fun applyThemeColor() {
        currentIsDarkModel = AutoThemeManager.isDarkModel()
        if (view is TextView) applyTextThemeColor()
        applyBGThemeColor()
    }

    private fun applyTextThemeColor() {
        if (view !is TextView) return
        if (currentIsDarkModel && themeViewParams.textDarkColor != null && themeViewParams.textDarkColor != view.textColors) {
            view.setTextColor(themeViewParams.textDarkColor)
        } else if (themeViewParams.lightTextColor != null && themeViewParams.lightTextColor != view.textColors) {
            view.setTextColor(themeViewParams.lightTextColor)
        }
    }

    private fun applyBGThemeColor() {
        val rippleEnable = themeViewParams.rippleEnable && (view.isClickable || view.isLongClickable)
        if (themeViewParams.bgLightColor == null || themeViewParams.bgDarColor == null || rippleEnable.not()) return

        val contentDrawable = createContentDrawable()
        view.background = if (rippleEnable && themeViewParams.rippleDarkColor != null && themeViewParams.rippleLightColor != null) {
            RippleDrawable(
                if (currentIsDarkModel) themeViewParams.rippleDarkColor!! else themeViewParams.rippleLightColor!!,
                contentDrawable,
                contentDrawable.constantState?.newDrawable()
            )
        } else {
            contentDrawable
        }
    }

    private fun createContentDrawable() : GradientDrawable {
        return object : GradientDrawable() {
            override fun onBoundsChange(r: Rect) {
                super.onBoundsChange(r)
                if (themeViewParams.isRadiusAdjustBounds) {
                    cornerRadius = (min(r.width(), r.height()) / 2).toFloat()
                }
            }
        }.apply {
            color = if (currentIsDarkModel) themeViewParams.bgDarColor else themeViewParams.bgLightColor
            if (themeViewParams.radiusTopLeft > 0 || themeViewParams.radiusTopRight > 0 || themeViewParams.radiusBottomLeft > 0 || themeViewParams.radiusBottomRight > 0) {
                cornerRadii = floatArrayOf(
                    themeViewParams.radiusTopLeft.toFloat(), themeViewParams.radiusTopLeft.toFloat(),
                    themeViewParams.radiusTopRight.toFloat(), themeViewParams.radiusTopRight.toFloat(),
                    themeViewParams.radiusBottomLeft.toFloat(), themeViewParams.radiusBottomLeft.toFloat(),
                    themeViewParams.radiusBottomRight.toFloat(), themeViewParams.radiusBottomRight.toFloat(),
                )
                themeViewParams.isRadiusAdjustBounds = false
            } else {
                if (themeViewParams.radius > 0) {
                    cornerRadius = themeViewParams.radius.toFloat()
                    themeViewParams.isRadiusAdjustBounds = false
                }
            }
            setStroke(themeViewParams.borderWith, if (currentIsDarkModel) themeViewParams.borderDarkColor else themeViewParams.borderLightColor)
        }
    }

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