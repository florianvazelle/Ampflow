package com.florianvazelle.ampflow

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_tool_battery, FragmentToolBattery.newInstance())
            .commit()

        findViewById<View>(R.id.button).setOnClickListener {
            startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
        }
    }
}