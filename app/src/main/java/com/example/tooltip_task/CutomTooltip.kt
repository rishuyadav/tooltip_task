package com.example.tooltip_task

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView

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

    fun setTargetView(targetView: View) {
        targetView.setOnLongClickListener {
            showTooltip(targetView, TooltipPosition.TOP) // Default position is set to top
            true
        }
    }

    fun setTooltipText(text: String) {
        tooltipText.text = text
    }
    fun setTooltipPadding(padding: Int) {
        tooltipView.setPadding(padding,padding,padding,padding)
    }

    fun setTooltipWidth(width: Int) {


    }



    fun showTooltip(targetView: View, position: TooltipPosition) {
        val location = IntArray(2)
        targetView.getLocationOnScreen(location)

        val xOffset: Int
        val yOffset: Int

        val tooltipWidth = popupWindow.width
        val tooltipHeight = popupWindow.height

        when (position) {
            TooltipPosition.TOP -> {
                xOffset = location[0] + targetView.width / 2 - tooltipWidth / 2
                yOffset = location[1] - targetView.height/2
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
                xOffset = location[0] - targetView.width/2
                yOffset = location[1]  - tooltipHeight / 2
            }
        }




        popupWindow.update(xOffset, yOffset, -1, -1) // Update the position
        popupWindow.showAtLocation(targetView, Gravity.NO_GRAVITY, xOffset, yOffset)
    }

    fun dismissTooltip() {
            popupWindow.dismiss()

    }

}
