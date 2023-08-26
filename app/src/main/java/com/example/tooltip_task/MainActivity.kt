package com.example.tooltip_task

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.tooltip_task.databinding.ActivityMainBinding
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.model.ColorSwatch

class MainActivity : AppCompatActivity() {

    private lateinit var customTooltip: CustomTooltip
    private lateinit var binding: ActivityMainBinding

    private var selectedTextColor: Int = Color.BLACK // Default text color
    private var selectedBackgroundColor: Int = Color.WHITE // Default background color
    private var isPickingTextColor: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        customTooltip = CustomTooltip(this)
        val targetButton: View = findViewById(R.id.buttonRenderTooltip)

        val positions = arrayOf("TOP", "RIGHT", "BOTTOM", "LEFT")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, positions)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerTooltipPosition.adapter = spinnerAdapter

        binding.buttonRenderTooltip.setOnClickListener {
            val tooltipText = binding.editTextTooltipText.text.toString()
            val position = when (binding.spinnerTooltipPosition.selectedItemPosition) {
                0 -> CustomTooltip.TooltipPosition.TOP
                1 -> CustomTooltip.TooltipPosition.RIGHT
                2 -> CustomTooltip.TooltipPosition.BOTTOM
                else -> CustomTooltip.TooltipPosition.LEFT
            }
            val tooltipWidth = binding.editTextTooltipWidth.text.toString().toIntOrNull() ?: 200
            val textSize = binding.editTextTextSize.text.toString().toFloatOrNull() ?: 14f // Default text size

            customTooltip.setTooltipText(tooltipText)

            customTooltip.tooltipText.textSize = textSize
            customTooltip.tooltipText.setTextColor(selectedTextColor)
            customTooltip.tooltipView.setBackgroundColor(selectedBackgroundColor)
             // Set background color
            customTooltip.showTooltip(targetButton, position)
            customTooltip.popupWindow.width = tooltipWidth
        }

        binding.buttonTextColorPicker.setOnClickListener {
            isPickingTextColor = true
            showColorPickerDialog(true) // Pass true to indicate picking text color
        }

        binding.buttonBackgroundColorPicker.setOnClickListener {
            isPickingTextColor = false
            showColorPickerDialog(false) // Pass false to indicate picking background color
        }
    }

    private fun showColorPickerDialog( boolean: Boolean) {
        val title = if (isPickingTextColor) "Pick Text Color" else "Pick Background Color"
        val defaultColor = if (isPickingTextColor) R.color.white else R.color.black

        MaterialColorPickerDialog.Builder(this)
            .setTitle(title)
            .setColorShape(ColorShape.CIRCLE)
            .setColorSwatch(ColorSwatch._500)
            .setDefaultColor(defaultColor)
            .setColorListener { color, colorHex ->
                if (isPickingTextColor) {
                    selectedTextColor = color
                    binding.buttonTextColorPicker.setTextColor(color)
                } else {
                    selectedBackgroundColor = color
                    binding.buttonBackgroundColorPicker.setTextColor(color)
                }
            }
            .show()
    }
}
