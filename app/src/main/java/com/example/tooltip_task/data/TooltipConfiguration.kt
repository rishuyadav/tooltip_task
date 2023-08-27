package com.example.tooltip_task.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tooltip_configurations")
data class TooltipConfiguration(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val buttonIndex: Int,
    val tooltipText: String,
    val tooltipPosition: String,
    val tooltipWidth: Int,
    val textSize: Float,
    val textColor: Int,
    val backgroundColor: Int,
    val tooltipPadding: Int
)
