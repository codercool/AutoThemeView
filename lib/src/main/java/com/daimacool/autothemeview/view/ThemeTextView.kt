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
import kotlin.math.min

class ThemeTextView : androidx.appcompat.widget.AppCompatTextView {

    private var currentIsDarkModel = false

    private var textDarkColor: ColorStateList? = null

    private var lightTextColor: ColorStateList? = null

    private var lightBGColor: ColorStateList? = null

    private var darBGColor: ColorStateList? = null

    private var radius: Int = 0
    private var mRadiusTopLeft = 0
    private var radiusTopRight = 0
    private var radiusBottomLeft = 0
    private var radiusBottomRight = 0
    private var isRadiusAdjustBounds = false

    private var borderWith = 0
    private var borderColor: ColorStateList? = null
    private var borderDarkColor: ColorStateList? = null

    private var rippleColor: ColorStateList? = null
    private var rippleDarkColor: ColorStateList? = null
    private var rippleEnable = false

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

        textDarkColor = typedArray.getColorStateList(R.styleable.ThemeTextView_theme_textDarkColor)
            ?: defaultParams.textDarkColor

        lightBGColor = typedArray.getColorStateList(R.styleable.ThemeTextView_theme_backgroundColor)
            ?: defaultParams.bgLightColor

        darBGColor =
            typedArray.getColorStateList(R.styleable.ThemeTextView_theme_backgroundDarkColor)
                ?: defaultParams.bgDarColor

        rippleColor = typedArray.getColorStateList(R.styleable.ThemeTextView_theme_rippleColor)
            ?: defaultParams.rippleLightColor

        rippleDarkColor =
            typedArray.getColorStateList(R.styleable.ThemeTextView_theme_rippleDarkColor)
                ?: defaultParams.rippleDarkColor

        radius = typedArray.getDimensionPixelSize(
            R.styleable.ThemeTextView_theme_radius,
            defaultParams.radius
        )

        mRadiusTopLeft =
            typedArray.getDimensionPixelSize(
                R.styleable.ThemeTextView_theme_radiusTopLeft,
                defaultParams.radiusTopLeft
            )

        radiusTopRight =
            typedArray.getDimensionPixelSize(
                R.styleable.ThemeTextView_theme_radiusTopRight,
                defaultParams.radiusTopRight
            )

        radiusBottomLeft =
            typedArray.getDimensionPixelSize(
                R.styleable.ThemeTextView_theme_radiusBottomLeft,
                defaultParams.radiusBottomLeft
            )

        radiusBottomRight =
            typedArray.getDimensionPixelSize(
                R.styleable.ThemeTextView_theme_radiusBottomRight,
                defaultParams.radiusBottomRight
            )

        borderWith =
            typedArray.getDimensionPixelSize(
                R.styleable.ThemeTextView_theme_borderWidth,
                defaultParams.borderWith
            )

        borderColor =
            typedArray.getColorStateList(R.styleable.ThemeTextView_theme_borderColor)
                ?: defaultParams.borderLightColor

        borderDarkColor =
            typedArray.getColorStateList(R.styleable.ThemeTextView_theme_borderDarkColor)
                ?: defaultParams.borderDarkColor

        isRadiusAdjustBounds =
            typedArray.getBoolean(
                R.styleable.ThemeTextView_theme_isRadiusAdjustBounds,
                defaultParams.isRadiusAdjustBounds
            )

        rippleEnable = typedArray.getBoolean(
            R.styleable.ThemeTextView_theme_rippleEnable,
            defaultParams.rippleEnable
        )
        typedArray.recycle()
        lightTextColor = textColors
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

        if (currentIsDarkModel && textDarkColor != null && textDarkColor != textColors) {
            setTextColor(textDarkColor)
        } else if (lightTextColor != null && lightTextColor != textColors) {
            setTextColor(lightTextColor)
        }
        applyBGThemeColor()
    }

    private fun applyBGThemeColor() {
        if (lightBGColor == null || darBGColor == null) return
        val contentDrawable = object : GradientDrawable() {
            override fun onBoundsChange(r: Rect) {
                super.onBoundsChange(r)
                if (isRadiusAdjustBounds) {
                    cornerRadius = (min(r.width(), r.height()) / 2).toFloat()
                }
            }
        }.apply {
            color = if (currentIsDarkModel) darBGColor else lightBGColor
            if (mRadiusTopLeft > 0 || radiusTopRight > 0 || radiusBottomLeft > 0 || radiusBottomRight > 0) {
                cornerRadii = floatArrayOf(
                    mRadiusTopLeft.toFloat(), mRadiusTopLeft.toFloat(),
                    radiusTopRight.toFloat(), radiusTopRight.toFloat(),
                    radiusBottomLeft.toFloat(), radiusBottomLeft.toFloat(),
                    radiusBottomRight.toFloat(), radiusBottomRight.toFloat(),
                )
                isRadiusAdjustBounds = false
            } else {
                if (radius > 0) {
                    cornerRadius = radius.toFloat()
                    isRadiusAdjustBounds = false
                }
            }
            setStroke(borderWith, if (currentIsDarkModel) borderDarkColor else borderColor)
        }

        if (rippleEnable && (isClickable || isLongClickable) && rippleDarkColor != null && rippleColor != null) {
            background = RippleDrawable(
                if (currentIsDarkModel) rippleDarkColor!! else rippleColor!!,
                contentDrawable,
                contentDrawable.constantState?.newDrawable()
            )
        } else {
            background = contentDrawable
        }
    }

    private fun updateClickBGDrawable() {
        val currentDrawable = background
        if (rippleEnable && rippleDarkColor != null && rippleColor != null && currentDrawable != null && currentDrawable !is RippleDrawable) {
            background = RippleDrawable(
                if (currentIsDarkModel) rippleDarkColor!! else rippleColor!!,
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