package com.example.tooltip_task.ui

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.tooltip_task.CustomTooltip
import com.example.tooltip_task.R
import com.example.tooltip_task.data.TooltipConfiguration
import com.example.tooltip_task.data.TooltipDatabase
import kotlinx.coroutines.launch
import timber.log.Timber

class PreviewActivity : AppCompatActivity() {

    private lateinit var tooltipDatabase: TooltipDatabase
    private var currentTooltip: CustomTooltip? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)

        Timber.d("PreviewActivity created")

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
            try {
                val tooltipConfigurations = tooltipDatabase.tooltipDao().getAllTooltipConfigurations()
                Timber.d("Retrieved ${tooltipConfigurations.size} tooltip configurations")

                tooltipConfigurations.forEach { configuration ->
                    val button = buttons.getOrNull(configuration.buttonIndex)
                    if (button == null) {
                        Timber.e("Button not found for index ${configuration.buttonIndex}")
                        return@forEach
                    }

                    dismissCurrentTooltip()

                    val customTooltip = createCustomTooltip(configuration)
                    showTooltip(customTooltip, button, configuration)

                    currentTooltip = customTooltip
                }
            } catch (e: Exception) {
                Timber.e(e, "Error while retrieving tooltip configurations")
            }
        }
    }

    private fun dismissCurrentTooltip() {
        currentTooltip?.dismissTooltip()
    }

    private fun createCustomTooltip(configuration: TooltipConfiguration): CustomTooltip {
        val customTooltip = CustomTooltip(this@PreviewActivity)
        customTooltip.setTooltipText(configuration.tooltipText)
        customTooltip.tooltipText.textSize = configuration.textSize
        customTooltip.tooltipText.setTextColor(configuration.textColor)
        customTooltip.tooltipView.setBackgroundColor(configuration.backgroundColor)
        customTooltip.setTooltipPadding(configuration.tooltipPadding)
        customTooltip.setTooltipWidth(configuration.tooltipWidth)
        return customTooltip
    }

    private fun showTooltip(customTooltip: CustomTooltip, button: Button, configuration: TooltipConfiguration) {
        val position = try {
            CustomTooltip.TooltipPosition.valueOf(configuration.tooltipPosition)
        } catch (e: IllegalArgumentException) {
            Timber.w("Invalid tooltip position: ${configuration.tooltipPosition}")
            CustomTooltip.TooltipPosition.TOP
        }

        customTooltip.showTooltip(button, position)
        customTooltip.popupWindow.width = configuration.tooltipWidth

        Timber.d("Tooltip shown for button at index ${configuration.buttonIndex}")
    }
}
