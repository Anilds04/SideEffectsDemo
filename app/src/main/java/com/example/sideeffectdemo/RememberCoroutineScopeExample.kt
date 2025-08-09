package com.example.sideeffectdemo

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.provider.FontsContractCompat.Columns
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/*
* This requires user action when button clicked then only side effect will be handled
* This is similar to launchedEffect but it doesn't run automatically
*
* */
@Composable
fun RememberCoroutineScopeExample() {

    val counter = remember { mutableStateOf(0) }
    val scope = rememberCoroutineScope()

    var text = "Timer Started ${counter.value}"

    if (counter.value == 10) {
        text = "Timer Stopped"
    }
    Column {
        Text(text = text)
        Button(onClick = {
            scope.launch {
                for (i in 0 until 10) {
                    counter.value++
                    delay(1000)
                }
            }
        }
        ) {

            Text(text = "Start timer")
        }

    }
}