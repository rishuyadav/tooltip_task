package com.example.tooltip_task

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import com.bumptech.glide.Glide
import timber.log.Timber

class CustomTooltip(private val context: Context) {

    val tooltipView: View = LayoutInflater.from(context).inflate(R.layout.layout_tooltip, null)
    val tooltipText: TextView = tooltipView.findViewById(R.id.customTooltipText)
    val popupWindow: PopupWindow = PopupWindow(tooltipView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

    enum class TooltipPosition {
        TOP,
        RIGHT,
        BOTTOM,
        LEFT,
    }

    init {
        Timber.tag("CustomTooltip")
    }

    fun setTargetView(targetView: View) {
        targetView.setOnLongClickListener {
            showTooltip(targetView, TooltipPosition.TOP) // Default position is set to top
            true
        }
    }

    fun setTooltipText(text: String) {
        tooltipText.text = text
        Timber.d("Tooltip text set: $text")
    }
    fun setTooltipImage(img: String?){
        val imagev = tooltipView.findViewById<ImageView>(R.id.customTooltipImage)
        Glide.with(context)
            .load(img)
            .into(imagev);

    }

    fun setTooltipPadding(padding: Int) {
        tooltipView.setPadding(padding, padding, padding, padding)
        Timber.d("Tooltip padding set: $padding")
    }

    fun setTooltipWidth(width: Int) {
        val textLayoutParams = tooltipText.layoutParams
        textLayoutParams.width = width
        tooltipText.layoutParams = textLayoutParams
        Timber.d("Tooltip width set: $width")
    }

    fun showTooltip(targetView: View, position: TooltipPosition) {
        val (xOffset, yOffset) = calculateOffsets(targetView, position)

        popupWindow.update(xOffset, yOffset, -1, -1)
        popupWindow.showAtLocation(targetView, Gravity.NO_GRAVITY, xOffset, yOffset)

        Timber.d("Tooltip shown at position: $position")
    }

    private fun calculateOffsets(targetView: View, position: TooltipPosition): Pair<Int, Int> {
        val location = IntArray(2)
        targetView.getLocationOnScreen(location)

        val xOffset: Int
        val yOffset: Int

        val tooltipWidth = popupWindow.width
        val tooltipHeight = popupWindow.height

        when (position) {
            TooltipPosition.TOP -> {
                xOffset = location[0] + targetView.width / 2 - tooltipWidth / 2
                yOffset = location[1] - targetView.height / 2
            }
            TooltipPosition.RIGHT -> {
                xOffset = location[0] + targetView.width
                yOffset = location[1] + targetView.height / 2 - tooltipHeight / 2
            }
            TooltipPosition.BOTTOM -> {
                xOffset = location[0] + targetView.width / 2 - tooltipWidth / 2
                yOffset = location[1] + targetView.height
            }
            TooltipPosition.LEFT -> {
                xOffset = location[0] - targetView.width / 2
                yOffset = location[1] - tooltipHeight / 2
            }
        }

        return xOffset to yOffset
    }

    fun dismissTooltip() {
        popupWindow.dismiss()
        Timber.d("Tooltip dismissed")
    }
}
