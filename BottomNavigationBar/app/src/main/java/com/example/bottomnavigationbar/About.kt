package com.example.bottomnavigationbar

import android.graphics.Paint.Align
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun About() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "About the App",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            Text("This app was created as part of FIT5046 exercise",
                style = MaterialTheme.typography.bodyLarge)
        }
    }
}