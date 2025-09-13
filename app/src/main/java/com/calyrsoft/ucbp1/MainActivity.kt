package com.calyrsoft.ucbp1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.calyrsoft.ucbp1.navigation.AppNavigation
import com.calyrsoft.ucbp1.ui.theme.Ucbp1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ucbp1Theme {
                AppNavigation()
            }
        }
    }
}