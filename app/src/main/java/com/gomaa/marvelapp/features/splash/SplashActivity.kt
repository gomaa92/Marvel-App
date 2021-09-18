package com.gomaa.marvelapp.features.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gomaa.marvelapp.R
import com.gomaa.marvelapp.features.list_characters.presentation.view.activity.ListCharactersActivity
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private var duration: Long = 3000

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        loadSplashScreen()
        blur()
    }

    private fun loadSplashScreen() {
        lifecycleScope.launch(Dispatchers.Main) {
            delay(duration)
            val intent = Intent(this@SplashActivity, ListCharactersActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun blur() {
        Glide.with(this).load(R.drawable.mcu_background)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 3)))
            .into(background)

    }
}