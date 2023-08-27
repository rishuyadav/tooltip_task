package com.example.tooltip_task.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.tooltip_task.R

class SplashActivity : AppCompatActivity() {

    private val splashTimeout: Long = 2000 // 2 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_splash)

        val animationView: LottieAnimationView = findViewById(R.id.animationView)
        setupAnimation(animationView)
        startMainActivityWithDelay()
    }

    private fun setupAnimation(animationView: LottieAnimationView) {
        animationView.setAnimation(R.raw.lottie)
        animationView.loop(true)
        animationView.playAnimation()
    }

    private fun startMainActivityWithDelay() {
        val handler = Handler()
        handler.postDelayed({
            startMainActivity()
        }, splashTimeout)
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
