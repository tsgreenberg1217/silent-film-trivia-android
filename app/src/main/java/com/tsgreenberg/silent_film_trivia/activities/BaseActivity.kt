package com.tsgreenberg.silent_film_trivia.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tsgreenberg.silent_film_trivia.Utils.LogingUtils

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogingUtils.log("onCreate- ${this::class.java.canonicalName}")
    }



    override fun onResume() {
        super.onResume()
        LogingUtils.log("onResume- ${this::class.java.canonicalName}")
    }

    override fun onStop() {
        LogingUtils.log("onStop- ${this::class.java.canonicalName}")
        super.onStop()
    }

    override fun onDestroy() {
        LogingUtils.log("onDestroy- ${this::class.java.canonicalName}")
        super.onDestroy()
    }
}