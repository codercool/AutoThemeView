package com.daimacool.autothemeview

import android.content.Context
import android.content.res.ColorStateList
import android.util.TypedValue

object AutoThemeManager {

    private var isDakModel = false

    private var defaultParams: ThemeViewDefaultParams? = null

    fun isDarkModel(): Boolean {
        return isDakModel
    }

    fun setDarkModel(isDark: Boolean) {
        isDakModel = isDark
    }

    fun setDefaultParams(defaultParams: ThemeViewDefaultParams) {
        this.defaultParams = defaultParams
    }

    fun getDefaultParams(): ThemeViewDefaultParams {
        if (defaultParams == null) {
            defaultParams = ThemeViewDefaultParams()
        }
        return defaultParams!!
    }

    class Builder(private val context: Context) {
        private val params = ThemeViewDefaultParams()

        fun build(): ThemeViewDefaultParams {
            return params
        }

        fun setTextDarkColor(textDarkColorRes: Int): Builder {
            params.textDarkColor = context.getColorStateList(textDarkColorRes)
            return this
        }

        fun setBGLightColor(lightBGColorRes: Int): Builder {
            params.bgLightColor = context.getColorStateList(lightBGColorRes)
            return this
        }

        fun setBGDarkColor(darkBGColorRes: Int): Builder {
            params.bgDarColor = context.getColorStateList(darkBGColorRes)
            return this
        }

        fun setRadius(radiusDP: Float): Builder {
            params.radius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, radiusDP, resources.displayMetrics)
                TypedValue.complexToDimensionPixelSize(radiusDP, context.resources.displayMetrics)
            return this
        }

        fun setRadiusTopLeft(radiusTopLeftDP: Int): Builder {
            params.radiusTopLeft =
                TypedValue.complexToDimensionPixelSize(
                    radiusTopLeftDP,
                    context.resources.displayMetrics
                )
            return this
        }


        fun setRadiusTopRight(radiusTopRightDP: Int): Builder {
            params.radiusTopRight =
                TypedValue.complexToDimensionPixelSize(
                    radiusTopRightDP,
                    context.resources.displayMetrics
                )
            return this
        }

        fun setRadiusBottomLeft(radiusBottomLeftDP: Int): Builder {
            params.radiusBottomLeft =
                TypedValue.complexToDimensionPixelSize(
                    radiusBottomLeftDP,
                    context.resources.displayMetrics
                )
            return this
        }

        fun setRadiusBottomRight(radiusBottomRightDP: Int): Builder {
            params.radiusBottomRight =
                TypedValue.complexToDimensionPixelSize(
                    radiusBottomRightDP,
                    context.resources.displayMetrics
                )
            return this
        }

        fun setIsRadiusAdjustBounds(isRadiusAdjustBounds: Boolean): Builder {
            params.isRadiusAdjustBounds = isRadiusAdjustBounds
            return this
        }

        fun setBorderWidth(borderWith:Int): Builder {
            params.borderWith = borderWith
            return this
        }

        fun setBorderLightColor(borderLightColorRes:Int): Builder {
            params.borderLightColor = context.getColorStateList(borderLightColorRes)
            return this
        }

        fun setBorderDarkColor(borderDarkColorRes:Int): Builder {
            params.borderDarkColor = context.getColorStateList(borderDarkColorRes)
            return this
        }

        fun setRippleLightColor(rippleLightColorRes:Int): Builder {
            params.rippleLightColor = context.getColorStateList(rippleLightColorRes)
            return this
        }

        fun setRippleDarkColorColor(rippleDarkColorRes:Int): Builder {
            params.rippleDarkColor = context.getColorStateList(rippleDarkColorRes)
            return this
        }

        fun setRippleEnable(rippleEnable:Boolean): Builder {
            params.rippleEnable = rippleEnable
            return this
        }
    }

    class ThemeViewDefaultParams {
        var textDarkColor: ColorStateList? = null
        var lightTextColor: ColorStateList? = null

        var bgLightColor: ColorStateList? = null
        var bgDarColor: ColorStateList? = null

        var radius: Int = 0
        var radiusTopLeft = 0
        var radiusTopRight = 0
        var radiusBottomLeft = 0
        var radiusBottomRight = 0
        var isRadiusAdjustBounds = false

        var borderWith = 0
        var borderLightColor: ColorStateList? = null
        var borderDarkColor: ColorStateList? = null

        var rippleLightColor: ColorStateList? = null
        var rippleDarkColor: ColorStateList? = null
        var rippleEnable = false
    }
}