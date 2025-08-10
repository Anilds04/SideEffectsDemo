package com.example.sideeffectdemo

import android.util.Log
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import kotlinx.coroutines.delay


/*
* Example 1 :
* In this if the value changed in RememberUpdatedStateExample method after 2 sec
* will be updated in Counter method inside LaunchedEffect which already running
* async or ling running task
* */

@Composable
fun RememberUpdatedStateExample() {

    val value = remember { mutableStateOf(0) }

    LaunchedEffect(key1 = Unit) {
        delay(2000)
        value.value = 10
    }

    Counter(value.value)
}

@Composable
fun Counter(value: Int) {
    val scope = rememberUpdatedState(value)
    LaunchedEffect(key1 = Unit) {
        delay(5000)
        Log.d("TAG", "Counter: ${scope.value}")
    }

    Text(text = "$value")
}



/*
* Example 2 update the method based of user input
* */

fun a(){
    Log.d("TAG", "A: Method A called")
}


fun b(){
    Log.d("TAG", "B: Method B called")
}

@Composable
fun App(){
    val value = remember { mutableStateOf(::a) }

  //  val scope = rememberCoroutineScope()
    Button(onClick = {value.value = ::b}) {
        Text("Update my value")
    }

    LandingScreen(value.value)
}


@Composable
fun LandingScreen(onClick: () -> Unit) {
    val onAction by  rememberUpdatedState(onClick)
    LaunchedEffect(key1 = Unit) {
        delay(5000)
        onAction()
    }
}