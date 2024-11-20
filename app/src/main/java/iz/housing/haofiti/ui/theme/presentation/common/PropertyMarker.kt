package iz.housing.haofiti.ui.theme.presentation.common

import android.content.Context
import android.graphics.Bitmap
import androidx.core.content.ContextCompat


fun createChipBitmap(context: Context, amount: Int): Bitmap {
    val drawable = DrawableEuroChip(context, amount)
    val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
    val canvas = android.graphics.Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bitmap
}

class DrawableEuroChip(context: Context, amount: Int) : android.graphics.drawable.Drawable() {
    private val paint = android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG)
    private val textPaint = android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG)
    private val borderPaint = android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG)
    private val text = "Ksh ${amount}K"
    private val blueColor = ContextCompat.getColor(context, android.R.color.holo_blue_light)
    private val whiteColor = android.graphics.Color.WHITE
    private val blackColor = android.graphics.Color.BLACK
    private var isClicked = false

    init {
        textPaint.textSize = 30f
        textPaint.textAlign = android.graphics.Paint.Align.CENTER
        borderPaint.style = android.graphics.Paint.Style.STROKE
        borderPaint.strokeWidth = 4f
        updateColors()
    }

    private fun updateColors() {
        if (isClicked) {
            paint.color = whiteColor
            textPaint.color = blackColor
            borderPaint.color = blueColor
        } else {
            paint.color = blueColor
            textPaint.color = whiteColor
            borderPaint.color = whiteColor
        }
    }

    override fun draw(canvas: android.graphics.Canvas) {
        val width = bounds.width().toFloat()
        val height = bounds.height().toFloat()

        // Draw chip body
        val rect = android.graphics.RectF(2f, 2f, width - 2f, height - 18f)
        canvas.drawRoundRect(rect, 18f, 18f, paint)

        // Draw border
        canvas.drawRoundRect(rect, 18f, 18f, borderPaint)

        // Draw text
        val textX = width / 2
        val textY = (height - 20f) / 2 - ((textPaint.descent() + textPaint.ascent()) / 2)
        canvas.drawText(text, textX, textY, textPaint)

        // Draw arrow
        val path = android.graphics.Path()
        path.moveTo(width / 2 - 20f, height - 20f)
        path.lineTo(width / 2 + 20f, height - 20f)
        path.lineTo(width / 2, height)
        path.close()
        canvas.drawPath(path, paint)
    }

    fun toggleClickState() {
        isClicked = !isClicked
        updateColors()
        invalidateSelf()
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
        textPaint.alpha = alpha
        borderPaint.alpha = alpha
    }

    override fun setColorFilter(colorFilter: android.graphics.ColorFilter?) {
        paint.colorFilter = colorFilter
        textPaint.colorFilter = colorFilter
        borderPaint.colorFilter = colorFilter
    }

    @Deprecated("Deprecated in Java",
        ReplaceWith("android.graphics.PixelFormat.TRANSLUCENT", "android")
    )
    override fun getOpacity(): Int = android.graphics.PixelFormat.TRANSLUCENT
    override fun getIntrinsicWidth(): Int = 140
    override fun getIntrinsicHeight(): Int = 80
}