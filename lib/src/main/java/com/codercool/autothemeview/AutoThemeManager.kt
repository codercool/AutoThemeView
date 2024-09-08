package com.codercool.autothemeview

import android.content.Context
import android.util.TypedValue

object AutoThemeManager {

    private var isDakModel = false

    private var defaultParams: ThemeViewParams? = null

    fun isDarkModel(): Boolean {
        return isDakModel
    }

    fun setDarkModel(isDark: Boolean) {
        isDakModel = isDark
    }

    fun setDefaultParams(defaultParams: ThemeViewParams) {
        this.defaultParams = defaultParams
    }

    fun getDefaultParams(): ThemeViewParams {
        if (defaultParams == null) {
            defaultParams = ThemeViewParams()
        }
        return defaultParams!!
    }

    class Builder(private val context: Context) {
        private val params = ThemeViewParams()

        fun build(): ThemeViewParams {
            return params
        }

        /**
         * 设置文字默认夜间颜色
         * 日间文字默认颜色系统默认已提供，如有需要请在主题中使用下列方式覆盖
         * <style name="TextAppearance">
         *   <item name="textColor">?textColorPrimary</item>
         * </style>
         * */
        fun setTextDarkColor(textDarkColorRes: Int): Builder {
            params.textDarkColor = context.getColorStateList(textDarkColorRes)
            return this
        }

        /**
         * 设置提示文字默认夜间颜色
         * 日间提示文字默认颜色系统默认已提供，如有需要请在主题中使用下列方式覆盖
         * <style name="TextAppearance">
         *    <item name="textColorHint">?textColorHint</item>
         * </style>
         * */
        fun setTextHintDarkColor(textHintDarkColorRes: Int): Builder {
            params.textHintDarkColor = context.getColorStateList(textHintDarkColorRes)
            return this
        }

        /**设置默认背景色*/
        fun setBGColor(lightBGColorRes: Int, darkBGColorRes: Int): Builder {
            params.bgLightColor = context.getColorStateList(lightBGColorRes)
            params.bgDarColor = context.getColorStateList(darkBGColorRes)
            return this
        }

        fun setRadius(radiusDP: Float): Builder {
            params.radius = applyDimension(radiusDP)
            return this
        }

        fun setRadiusTopLeft(radiusTopLeftDP: Float): Builder {
            params.radiusTopLeft = applyDimension(radiusTopLeftDP)
            return this
        }


        fun setRadiusTopRight(radiusTopRightDP: Float): Builder {
            params.radiusTopRight = applyDimension(radiusTopRightDP)
            return this
        }

        fun setRadiusBottomLeft(radiusBottomLeftDP: Float): Builder {
            params.radiusBottomLeft = applyDimension(radiusBottomLeftDP)
            return this
        }

        fun setRadiusBottomRight(radiusBottomRightDP: Float): Builder {
            params.radiusBottomRight = applyDimension(radiusBottomRightDP)
            return this
        }

        fun setIsRadiusAdjustBounds(isRadiusAdjustBounds: Boolean): Builder {
            params.isRadiusAdjustBounds = isRadiusAdjustBounds
            return this
        }

        fun setBorderWidth(borderWith: Float): Builder {
            params.borderWith = applyDimension(borderWith)
            return this
        }

        fun setBorderLightColor(borderLightColorRes: Int): Builder {
            params.borderLightColor = context.getColorStateList(borderLightColorRes)
            return this
        }

        fun setBorderDarkColor(borderDarkColorRes: Int): Builder {
            params.borderDarkColor = context.getColorStateList(borderDarkColorRes)
            return this
        }

        fun setRippleLightColor(rippleLightColorRes: Int): Builder {
            params.rippleLightColor = context.getColorStateList(rippleLightColorRes)
            return this
        }

        fun setRippleDarkColorColor(rippleDarkColorRes: Int): Builder {
            params.rippleDarkColor = context.getColorStateList(rippleDarkColorRes)
            return this
        }

        fun setRippleEnable(rippleEnable: Boolean): Builder {
            params.rippleEnable = rippleEnable
            return this
        }

        private fun applyDimension(sizeDP: Float): Int {
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                sizeDP,
                context.resources.displayMetrics
            ).toInt()
        }
    }
}