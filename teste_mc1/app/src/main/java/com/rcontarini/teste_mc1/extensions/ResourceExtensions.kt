package com.rcontarini.teste_mc1.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat




fun Context.getDrawableCompat(@DrawableRes drawableResId: Int) : Drawable? {
    return AppCompatResources.getDrawable(this, drawableResId)
}