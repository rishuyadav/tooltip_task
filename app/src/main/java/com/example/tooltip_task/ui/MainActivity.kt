package com.example.tooltip_task.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.tooltip_task.CustomTooltip
import com.example.tooltip_task.R
import com.example.tooltip_task.data.TooltipConfiguration
import com.example.tooltip_task.data.TooltipDatabase
import com.example.tooltip_task.databinding.ActivityMainBinding
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.model.ColorSwatch
import kotlinx.coroutines.launch
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var customTooltip: CustomTooltip
    private lateinit var tooltipDatabase: TooltipDatabase

    private var selectedTextColor: Int = Color.BLACK
    private var selectedBackgroundColor: Int = Color.WHITE

    private var isPickingTextColor: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
        setupListeners()

        tooltipDatabase = TooltipDatabase.getDatabase(this)
        customTooltip = CustomTooltip(this)

        Timber.plant(Timber.DebugTree()) // Initialize Timber logging
        Timber.d("MainActivity created")
    }

    private fun setupViews() {
        val buttonNames = arrayOf("Button1", "Button2", "Button3", "Button4", "Button5")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, buttonNames)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerSelectButton.adapter = spinnerAdapter

        val positions = arrayOf("TOP", "RIGHT", "BOTTOM", "LEFT")
        val positionAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, positions)
        positionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerTooltipPosition.adapter = positionAdapter

        val images = arrayOf("Dog","Arrow","Apple","Dot")
        val imageAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, images)
        imageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerSelectImage.adapter = imageAdapter
    }

    private fun setupListeners() {
        binding.buttonRenderTooltip.setOnClickListener { renderTooltip() }

        binding.buttonTextColorPicker.setOnClickListener {
            isPickingTextColor = true
            showColorPickerDialog(true)
        }

        binding.buttonBackgroundColorPicker.setOnClickListener {
            isPickingTextColor = false
            showColorPickerDialog(false)
        }
    }

    private fun renderTooltip() {
        Timber.d("Rendering tooltip")

        val selectedButtonIndex = binding.spinnerSelectButton.selectedItemPosition
        val imgIndex = binding.spinnerSelectImage.selectedItemPosition
        val tooltipText = binding.editTextTooltipText.text.toString()
        val tooltipPadding = binding.editTextTooltipPadding.text.toString().toIntOrNull() ?: 5

        val position = when (binding.spinnerTooltipPosition.selectedItemPosition) {
            0 -> CustomTooltip.TooltipPosition.TOP
            1 -> CustomTooltip.TooltipPosition.RIGHT
            2 -> CustomTooltip.TooltipPosition.BOTTOM
            else -> CustomTooltip.TooltipPosition.LEFT
        }
        val tooltipWidth = binding.editTextTooltipWidth.text.toString().toIntOrNull() ?: 200
        val textSize = binding.editTextTextSize.text.toString().toFloatOrNull() ?: 14f

        customTooltip.setTooltipText(tooltipText)
        customTooltip.tooltipText.textSize = textSize
        customTooltip.tooltipText.setTextColor(selectedTextColor)
        customTooltip.tooltipView.setBackgroundColor(selectedBackgroundColor)

        val tooltipConfiguration = TooltipConfiguration(
            buttonIndex = selectedButtonIndex,
            tooltipText = tooltipText,
            tooltipPosition = position.name,
            tooltipWidth = tooltipWidth,
            textSize = textSize,
            textColor = selectedTextColor,
            backgroundColor = selectedBackgroundColor,
            tooltipPadding = tooltipPadding,
            imageIndex = imgIndex
        )

        lifecycleScope.launch {
            tooltipDatabase.tooltipDao().insertTooltipConfiguration(tooltipConfiguration)
            Timber.d("Tooltip configuration inserted into database")
        }

        navigateToPreviewActivity()
    }

    private fun showColorPickerDialog(isTextColor: Boolean) {
        val title = if (isTextColor) "Pick Text Color" else "Pick Background Color"
        val defaultColor = if (isTextColor) R.color.white else R.color.black

        MaterialColorPickerDialog.Builder(this)
            .setTitle(title)
            .setColorShape(ColorShape.CIRCLE)
            .setColorSwatch(ColorSwatch._500)
            .setDefaultColor(defaultColor)
            .setColorListener { color, colorHex ->
                if (isTextColor) {
                    selectedTextColor = color
                    binding.buttonTextColorPicker.setTextColor(color)
                    Timber.d("Text color selected: $colorHex")
                } else {
                    selectedBackgroundColor = color
                    binding.buttonBackgroundColorPicker.setTextColor(color)
                    Timber.d("Background color selected: $colorHex")
                }
            }
            .show()
    }

    private fun navigateToPreviewActivity() {
        Timber.d("Navigating to PreviewActivity")
        val intent = Intent(this, PreviewActivity::class.java)
        startActivity(intent)
    }
}
