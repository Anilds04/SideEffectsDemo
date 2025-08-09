package com.example.sideeffectdemo

import android.util.Log
import android.widget.Button
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlinx.coroutines.delay

/*
* Here when ever key changes the code inside launchedEffect will be called
*
* */
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



@Composable
fun LaunchedEffectTimer(){

    val counter = remember { mutableStateOf(0) }

    LaunchedEffect(key1 = Unit) {
        try {
            for (i in 1..10) {
                counter.value++
                delay(1000)
            }
        }
        catch (e : Exception){
            Log.d("TAG", "LaunchedEffectTimer: ${e.message  }")
        }
    }


    val value = counter.value
    var text = "Counter is running : $value"
    if(value ==10)
        text = "Counter Stopped"
    Text(text = text)
}