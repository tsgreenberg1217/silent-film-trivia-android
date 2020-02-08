package com.example.silent_film_trivia.Utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.silent_film_trivia.R

class FragmentUtils {
    companion object {
        fun replaceFragment(
            fm: FragmentManager,
            fragment: Fragment,
            container: Int,
            anim: Boolean = true
        ) = fm.beginTransaction().apply {
//            addToBackStack(null)
            if (anim) setCustomAnimations(R.anim.in_from_right, R.anim.out_to_left, 0, 0)
            replace(container, fragment)
            commit()
        }


    }
}