package com.example.sideeffectdemo

import android.util.Log
import android.widget.Button
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


@Composable
fun LaunchedEffectExample() {

    var count  =  remember { mutableStateOf(0) }

    val key = count.value % 3 == 0
    LaunchedEffect(key1 = key) {
        Log.d("TAG", "LaunchedEffectExample: Counter ----- ${count.value}")
    }

    Button(onClick = { count.value++}) {
        Text(text = "Click Me")
    }
}