package com.daimacool.autothemeview

object AutoThemeManager {

    private var isDakModel = false

    fun isDarkModel() : Boolean{
        return isDakModel
    }

    fun setDarkModel(isDark:Boolean) {
        isDakModel = isDark
    }
}