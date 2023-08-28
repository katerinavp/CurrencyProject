package com.katerinavp.core_ui.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.Rect
import android.util.AttributeSet
import androidx.core.graphics.toRectF
import com.google.android.material.appbar.AppBarLayout
import com.katerinavp.core_ui.R


class ClipAppBar(context: Context, attrs: AttributeSet) : AppBarLayout(context, attrs) {
    private val clipPath = Path()
    private val rect = Rect()
    private var cornets: Float

    init {
        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.ClipAppBar, 0, 0)
        cornets = a.getDimensionPixelSize(R.styleable.ClipAppBar_cornets, 0).toFloat()
        a.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        canvas.getClipBounds(rect)
        clipPath.addRoundRect(
            rect.toRectF(),
            arrayOf(0f, 0f, 0f, 0f, cornets, cornets, cornets, cornets).toFloatArray(),
            Path.Direction.CW
        )
        canvas.clipPath(clipPath)
        super.onDraw(canvas)
    }
}