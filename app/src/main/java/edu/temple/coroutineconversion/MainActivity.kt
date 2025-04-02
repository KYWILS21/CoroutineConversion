package edu.temple.coroutineconversion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private val cakeImageView: ImageView by lazy {
        findViewById(R.id.imageView)
    }

    private val currentTextView: TextView by lazy {
        findViewById(R.id.currentTextView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.revealButton).setOnClickListener {
            lifecycleScope.launch(Dispatchers.Default) {
                repeat(100) { opacity ->
                    withContext(Dispatchers.Main) {
                        currentTextView.text = String.format(Locale.getDefault(), "Current opacity: %d", opacity)
                        cakeImageView.alpha = opacity / 100f                    }
                    delay(40)
                }
            }
        }
    }
}