package com.rudachenkoroman.aston_intensiv_2.custom_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.rudachenkoroman.aston_intensiv_2.util.SelectColor

class CustomCircleView (context: Context, attrs: AttributeSet) : View(context, attrs){

    private val paint = Paint()
    private val colors = SelectColor.entries.map { it.color }

    init {
        paint.style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas) {
        val centerX = width / CENTER_FACT_CIRCLE
        val centerY = height / CENTER_FACT_CIRCLE
        val radius = minOf(centerX, centerY)
        val sweepAngle = FULL_ANGLE / colors.size

        for (i in colors.indices) {
            paint.color = colors[i]
            canvas.drawArc(
                centerX - radius,
                centerY - radius,
                centerX + radius,
                centerY + radius,
                i * sweepAngle, sweepAngle, USE_CENTER, paint
            )
        }
        super.onDraw(canvas)
    }

    fun getColor() = SelectColor.entries.random()

    companion object {
        private const val CENTER_FACT_CIRCLE = 2f
        private const val FULL_ANGLE = 360f
        private const val USE_CENTER = true

    }
}