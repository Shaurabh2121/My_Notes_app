package com.example.mynote.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.mynote.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var navController =Navigation.findNavController(this,R.id.fragment)
        NavigationUI.setupActionBarWithNavController(this,navController)
//        NavigationUI.setupActionBarWithNavController(this,navController)


    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            Navigation.findNavController(this,R.id.fragment),
            null
        )
    }
}