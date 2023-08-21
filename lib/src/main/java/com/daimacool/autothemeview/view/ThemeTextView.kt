package com.daimacool.autothemeview.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Rect
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.util.AttributeSet
import android.view.View
import com.daimacool.autothemeview.AutoThemeManager
import com.daimacool.autothemeview.R
import com.daimacool.autothemeview.ThemeViewParams
import kotlin.math.min

class ThemeTextView : androidx.appcompat.widget.AppCompatTextView {

    private var currentIsDarkModel = false

    private val themeViewParams = ThemeViewParams()

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
        themeViewParams.lightTextColor = textColors
        applyThemeColor()
    }

    override fun setTextAppearance(resId: Int) {
        super.setTextAppearance(resId)
        applyThemeColor()
    }

    override fun setOnClickListener(l: OnClickListener?) {
        super.setOnClickListener(l)
        if (isClickable) updateClickBGDrawable()
    }

    override fun setOnLongClickListener(l: OnLongClickListener?) {
        super.setOnLongClickListener(l)
        if (isLongClickable) updateClickBGDrawable()
    }

    private fun applyThemeColor() {
        currentIsDarkModel = AutoThemeManager.isDarkModel()

        if (currentIsDarkModel && themeViewParams.textDarkColor != null && themeViewParams.textDarkColor != textColors) {
            setTextColor(themeViewParams.textDarkColor)
        } else if (themeViewParams.lightTextColor != null && themeViewParams.lightTextColor != textColors) {
            setTextColor(themeViewParams.lightTextColor)
        }
        applyBGThemeColor()
    }

    private fun applyBGThemeColor() {
        val rippleEnable = themeViewParams.rippleEnable && (isClickable || isLongClickable)
        if (themeViewParams.bgLightColor == null || themeViewParams.bgDarColor == null || rippleEnable.not()) return

        val contentDrawable = createContentDrawable()
        background = if (rippleEnable && themeViewParams.rippleDarkColor != null && themeViewParams.rippleLightColor != null) {
            RippleDrawable(
                    if (currentIsDarkModel) themeViewParams.rippleDarkColor!! else themeViewParams.rippleLightColor!!,
                    contentDrawable,
                    contentDrawable.constantState?.newDrawable()
            )
        } else {
            contentDrawable
        }
    }

    private fun createContentDrawable() :GradientDrawable{
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

    private fun updateClickBGDrawable() {
        val currentDrawable = background ?: createContentDrawable()
        if (themeViewParams.rippleEnable && themeViewParams.rippleDarkColor != null && themeViewParams.rippleLightColor != null && currentDrawable !is RippleDrawable) {
            background = RippleDrawable(
                if (currentIsDarkModel) themeViewParams.rippleDarkColor!! else themeViewParams.rippleLightColor!!,
                currentDrawable,
                currentDrawable.constantState?.newDrawable()
            )
        }
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        if (visibility == View.VISIBLE) {
            if (currentIsDarkModel != AutoThemeManager.isDarkModel()) {
                applyThemeColor()
            }
        }
    }
}