package com.example.tooltip_task

import android.app.Application
import timber.log.Timber

class TooltipApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(object : Timber.DebugTree() {

            override fun log(
                priority: Int, tag: String?, message: String, t: Throwable?
            ) {
                super.log(priority, "global_tag_$tag", message, t)
            }
        })

        // USAGE
        Timber.d("App created!")

    }
}