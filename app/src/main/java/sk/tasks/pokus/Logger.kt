package sk.tasks.pokus

import android.content.Context
import android.util.Log
import android.widget.Toast

private val TAG = "AAA"

fun Context.logErr(message: String) {
    Log.e(TAG, message)
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.log(message: String, showToast: Boolean = false) {
    Log.v(TAG, message)

    if (showToast)
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}