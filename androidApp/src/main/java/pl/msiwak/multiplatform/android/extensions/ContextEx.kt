package pl.msiwak.multiplatform.android.extensions

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent

fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("no activity")
}

fun Context.openMailApp() {
    val intent = Intent(Intent.ACTION_MAIN)
    intent.addCategory(Intent.CATEGORY_APP_EMAIL)
    findActivity().startActivity(intent)
}
