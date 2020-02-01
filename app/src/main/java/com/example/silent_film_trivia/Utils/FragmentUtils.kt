package com.example.silent_film_trivia.Utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentUtils {
    companion object {
        fun replaceFragment(fm: FragmentManager, fragment: Fragment, container: Int) {
            fm.beginTransaction()
                .replace(container, fragment)
                .addToBackStack(null)
                .commit()
        }

    }
}