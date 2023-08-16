package com.example.golinkproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import com.example.golinkproject.databinding.ActivitySplashBinding
import okhttp3.internal.http.HTTP_INSUFFICIENT_SPACE_ON_RESOURCE

class SplashActivity : AppCompatActivity() {
    lateinit var logoImage: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        logoImage = findViewById(R.id.logoImage)
        logoImage.animate().setDuration(1000).translationY(4000F).setStartDelay(2000)

        var handler= Handler()
        handler.postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        },3000 )

    }
}