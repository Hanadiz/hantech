package com.hantech

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textView = TextView(this).apply {
            text = "Welcome to Han-Tech Ltd\n\nCommunity IT Services\n\n(Successfully built with Kotlin)"
            textSize = 22f
            textAlignment = TextView.TEXT_ALIGNMENT_CENTER
            setPadding(50, 200, 50, 50)
        }
        setContentView(textView)
    }
}
