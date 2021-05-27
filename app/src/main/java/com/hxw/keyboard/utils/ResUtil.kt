package com.hxw.keyboard.utils

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.TypedValue
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.hxw.keyboard.R


object ResUtil {
    fun getString(@StringRes id: Int): String {
        return context().getString(id)
    }

    fun getString(@StringRes resId: Int, vararg formatArgs: Any?): String {
        return context().getString(resId, *formatArgs)
    }

    fun getColor(@ColorRes id: Int): Int {
        return ContextCompat.getColor(context(), id)
    }

    fun getColorStateList(@ColorRes id: Int): ColorStateList? {
        return ContextCompat.getColorStateList(context(), id)
    }

    private fun context(): Context {
        return AppGlobals.get() as Context
    }

    fun getDrawable(@DrawableRes drawableId: Int): Drawable? {
        return ContextCompat.getDrawable(context(), drawableId)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getColorPrimary(): Int {
        val typedValue = TypedValue()
        context().theme.resolveAttribute(R.attr.colorPrimary, typedValue, true)
        return typedValue.data
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getDarkColorPrimary(): Int {
        val typedValue = TypedValue()
        context().theme.resolveAttribute(R.attr.colorPrimaryDark, typedValue, true)
        return typedValue.data
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getColorAccent(): Int {
        val typedValue = TypedValue()
        context().theme.resolveAttribute(R.attr.colorAccent, typedValue, true)
        return typedValue.data
    }
}