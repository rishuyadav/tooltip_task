package com.example.tooltip_task.data


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TooltipDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTooltipConfiguration(configuration: TooltipConfiguration)

    @Query("SELECT * FROM tooltip_configurations")
    suspend fun getAllTooltipConfigurations(): List<TooltipConfiguration>
}
