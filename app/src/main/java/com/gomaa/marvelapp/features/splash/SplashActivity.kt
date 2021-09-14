package com.gomaa.marvelapp.features.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.gomaa.marvelapp.R
import com.gomaa.marvelapp.features.list_characters.presentation.view.activity.ListCharactersActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private var duration: Long = 3000

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        loadSplashScreen()
    }

    private fun loadSplashScreen() {
        lifecycleScope.launch(Dispatchers.Main) {
            delay(duration)
            val intent = Intent(this@SplashActivity, ListCharactersActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}