package com.example.android2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android2.R
import com.example.android2.view.details.DetailsFragment
import com.example.android2.view.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                //.replace(R.id.container, DetailsFragment.newInstance()).commitNow()
                .replace(R.id.container, MainFragment.newInstance()).commitNow()
        }
    }
}