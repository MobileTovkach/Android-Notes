package com.example.air.androidnotes.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.air.androidnotes.R
import com.example.air.androidnotes.liseners.OnBackPressedListener
import com.example.air.androidnotes.ui.fragment.newInstanceNoteFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            replaceFragment(newInstanceNoteFragment())
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.content, fragment)
                .commit()
    }

    override fun onBackPressed() {
        val fm = supportFragmentManager
        var backPressedListener: OnBackPressedListener? = null
        for (fragment in fm.fragments) if (fragment is OnBackPressedListener) {
            backPressedListener = fragment
            break
        }

        when {
            backPressedListener != null -> backPressedListener.onBackPressed()
            else -> super.onBackPressed()
        }
    }


}
