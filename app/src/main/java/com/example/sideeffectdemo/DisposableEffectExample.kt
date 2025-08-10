package com.example.sideeffectdemo

import android.media.MediaPlayer
import android.util.Log
import android.view.ViewTreeObserver
import android.widget.EditText
import androidx.activity.compose.BackHandler
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/*
* Here media player resources will be released once the composable in removed
*
*
* */
@Composable
fun DisposableEffectExample() {

    val context = LocalContext.current

    DisposableEffect(Unit) {
        val player = MediaPlayer.create(context, R.raw.sample_12s)

        player.start()
        onDispose {
            player.stop()
            player.release()
        }
    }

}


@Composable
fun keyBoard() {
    keyBoardComposable()
    TextField(value = "", onValueChange = { })
}

@Composable
fun keyBoardComposable() {
    val view = LocalView.current
    var lastIsVisible by remember { mutableStateOf<Boolean?>(null) }

    DisposableEffect(key1 = Unit) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val insets = ViewCompat.getRootWindowInsets(view)
            val isKeyVisible = insets?.isVisible(WindowInsetsCompat.Type.ime())

            // Only log when value changes
            if (lastIsVisible != isKeyVisible) {
                lastIsVisible = isKeyVisible
                Log.d("TAG", "Keyboard visible: $isKeyVisible")
            }
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(listener)

        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }
}
