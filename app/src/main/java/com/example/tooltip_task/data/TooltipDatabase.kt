package com.example.tooltip_task.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TooltipConfiguration::class], version = 1, exportSchema = false)
abstract class TooltipDatabase : RoomDatabase() {

    abstract fun tooltipDao(): TooltipDao

    companion object {
        @Volatile
        private var INSTANCE: TooltipDatabase? = null

        fun getDatabase(context: Context): TooltipDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TooltipDatabase::class.java,
                    "tooltip_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
