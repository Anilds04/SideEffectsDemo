package com.example.sideeffectdemo

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


var counter = 1

/* Here HasSideEffect can call multiple times and counter will be incremented
 * many times and it is out of scope if some other composable consuming counter
 * those will be affected.
 * */
@Composable
fun HasSideEffect(){
    counter++
    Text(text = "I am Side Effect")
}

@Composable
fun ListComposable(){
    val categories = remember { mutableStateOf( emptyList<String>()) }
    categories.value = fetchCategories()

    LazyColumn {
        items(categories.value){
            Text(text = it)
        }
    }
}


fun fetchCategories() : List<String>{
    return listOf("Anil", "fug", "ogp")
}