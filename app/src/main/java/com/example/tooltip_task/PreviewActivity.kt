package com.example.tooltip_task

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.tooltip_task.data.TooltipDatabase
import kotlinx.coroutines.launch

class PreviewActivity : AppCompatActivity() {

    private lateinit var tooltipDatabase: TooltipDatabase
    private var currentTooltip: CustomTooltip? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)

        tooltipDatabase = TooltipDatabase.getDatabase(this)

        val buttons = arrayOf(
            findViewById<Button>(R.id.button),
            findViewById<Button>(R.id.button2),
            findViewById<Button>(R.id.button3),
            findViewById<Button>(R.id.button4),
            findViewById<Button>(R.id.button5)
        )

        // Retrieve and display tooltips for each button
        lifecycleScope.launch {
            val tooltipConfigurations = tooltipDatabase.tooltipDao().getAllTooltipConfigurations()
            tooltipConfigurations.forEach { configuration ->
                val button = buttons[configuration.buttonIndex]
                currentTooltip?.dismissTooltip()
                val customTooltip = CustomTooltip(this@PreviewActivity)

                customTooltip.setTooltipText(configuration.tooltipText)
                customTooltip.tooltipText.textSize = configuration.textSize
                customTooltip.tooltipText.setTextColor(configuration.textColor)
                customTooltip.tooltipView.setBackgroundColor(configuration.backgroundColor)
                customTooltip.setTooltipPadding(configuration.tooltipPadding)
                customTooltip.setTooltipWidth(configuration.tooltipWidth)

                val position = when (configuration.tooltipPosition) {
                    "TOP" -> CustomTooltip.TooltipPosition.TOP
                    "RIGHT" -> CustomTooltip.TooltipPosition.RIGHT
                    "BOTTOM" -> CustomTooltip.TooltipPosition.BOTTOM
                    "LEFT" -> CustomTooltip.TooltipPosition.LEFT
                    else -> CustomTooltip.TooltipPosition.TOP
                }

                customTooltip.showTooltip(button, position)
                customTooltip.popupWindow.width = configuration.tooltipWidth
                currentTooltip = customTooltip
            }
        }
    }
}
