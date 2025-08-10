package com.example.sideeffectdemo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ProduceStateScope
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay


/*
* Launched effect and produce state both are similar
* producestate return state and LaunchedEffect return unit
* use producestate if you want to create state use launchedstate for existing state
*
* */
@Composable
fun ProduceStateExample() {

    val state = produceState(0) {
        for (i in 1..10) {
            delay(1000)
            value += 1
        }
    }
    /*    Same in LunchedEffect
        LaunchedEffect(key1 = Unit) {
            for (i in 1..10) {
                delay(1000)
                state.value += 1
            }
        }*/
    Text(
        text = "${state.value}",
        style = MaterialTheme.typography.titleLarge,
        fontSize = 100.sp
    )
}


@Composable
fun Loader() {

    val state = produceState(0) {
        while (true) {
            delay(16)
            value = (value + 10) % 360
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(1f)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                imageVector = Icons.Default.Refresh,
                modifier = Modifier
                    .size(60.dp)
                    .rotate(state.value.toFloat()),
                contentDescription = ""
            )
            Text(text = "Refresh")
        }
    }
}
