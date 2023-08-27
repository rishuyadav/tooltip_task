package com.example.tooltip_task

import android.content.Intent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val animationView: LottieAnimationView = findViewById(R.id.animationView)

        // Set the animation file
        animationView.setAnimation(R.raw.lottie)

        // Set loop and play animation
        animationView.loop(true)
        animationView.playAnimation()

        // Wait for a few seconds before transitioning to the next activity
        val splashTimeout = 3000L // 3 seconds
        animationView.postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, splashTimeout)
    }
}
