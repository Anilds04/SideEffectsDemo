package com.example.sideeffectdemo

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

/*
* Derived from another state
* here number is a state and index is a producestate
* */
@SuppressLint("UnrememberedMutableState")
@Composable
fun DerivedStateExample() {

    val number = remember { mutableStateOf(2) }
    val index = produceState(1) {

        repeat(9){
            delay(1000)
            value +=1
        }
    }

    /*++*/


    val message = derivedStateOf{
        "${number.value} * ${index.value} = ${number.value*index.value}"
    }

    Box(modifier = Modifier.fillMaxSize(1f),
        contentAlignment = Alignment.Center){
        Text(text = message.value,
            fontSize = (25.sp),
            style = MaterialTheme.typography.labelLarge)
    }


}