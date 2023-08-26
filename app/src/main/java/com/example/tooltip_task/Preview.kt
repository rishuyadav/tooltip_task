package com.example.tooltip_task
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.tooltip_task.databinding.ActivityPreviewBinding

class PreviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPreviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreviewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Retrieve tooltip properties from the intent
        val tooltipText = intent.getStringExtra("tooltipText") ?: ""
        val tooltipPosition = intent.getSerializableExtra("tooltipPosition") as CustomTooltip.TooltipPosition
        val tooltipWidth = intent.getIntExtra("tooltipWidth", 200)
        val textSize = intent.getFloatExtra("textSize", 14f)
        val textColor = intent.getIntExtra("textColor", Color.BLACK)
        val backgroundColor = intent.getIntExtra("backgroundColor", Color.WHITE)

        // Find the target button based on the button ID passed in the intent
        val buttonId = intent.getIntExtra("targetButtonId", 0)
        val targetButton: View = findViewById(R.id.button3)

        // Configure and show the custom tooltip
        val customTooltip = CustomTooltip(this@PreviewActivity)
        customTooltip.setTooltipText(tooltipText)
        customTooltip.tooltipText.textSize = textSize
        customTooltip.tooltipText.setTextColor(textColor)
        customTooltip.tooltipView.setBackgroundColor(backgroundColor)
        customTooltip.showTooltip(targetButton, tooltipPosition)
        customTooltip.popupWindow.width = tooltipWidth
    }
}
