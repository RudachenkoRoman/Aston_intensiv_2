package com.rudachenkoroman.aston_intensiv_2.custom_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.rudachenkoroman.aston_intensiv_2.util.Colors
import com.rudachenkoroman.aston_intensiv_2.util.SelectColor

class CustomTextView (context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val widths: FloatArray
    private val width: Float

    var text = ""
    private var color = Color.GRAY
    private val fontPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        fontPaint.textSize = FONT_SIZE
        fontPaint.style = Paint.Style.FILL
        fontPaint.isFakeBoldText = true
        width = fontPaint.measureText(text)
        widths = FloatArray(text.length)
        fontPaint.getTextWidths(text, widths)
    }

    override fun onDraw(canvas: Canvas) {
        val centerX = width / CENTER_FACT_TEXT
        val textHeight = fontPaint.descent() - fontPaint.ascent()
        fontPaint.color = color
        canvas.drawText(text, centerX, textHeight, fontPaint)
    }

    fun setColor(selectedColor: SelectColor) {
        color =  when(selectedColor) {
            SelectColor.RED -> Color.RED
            SelectColor.ORANGE -> Color.parseColor(Colors.ORANGE_COLOR)
            SelectColor.YELLOW -> Color.parseColor(Colors.YELLOW_COLOR)
            SelectColor.GREEN -> Color.GREEN
            SelectColor.CYAN -> Color.CYAN
            SelectColor.BLUE -> Color.BLUE
            SelectColor.PURPLE -> Color.parseColor(Colors.PURPLE_COLOR)
        }
    }


    companion object {
        private const val CENTER_FACT_TEXT = 2f
        private const val FONT_SIZE = 80f
    }
}