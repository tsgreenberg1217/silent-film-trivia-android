package com.example.silent_film_trivia.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.silent_film_trivia.Utils.LogingUtils
import com.example.silent_film_trivia.interfaces.SessionFragmentListener

open class BaseFragment : Fragment() {

    internal var mListener: SessionFragmentListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogingUtils.log("onStop- ${this::class.java.canonicalName}")
    }

    override fun onResume() {
        super.onResume()
        LogingUtils.log("onResume- ${this::class.java.canonicalName}")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SessionFragmentListener) {
            mListener = context
        }
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