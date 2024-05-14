package pl.msiwak.multiplatform.ui.commonComponent.extensions

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri

private const val URI_MARKET = "market://details?id="
private const val URI_STORE = "https://play.google.com/store/apps/details?id="

fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("no activity")
}

fun openStore(context: Context) = with(context) {
    try {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("${URI_MARKET}${context.packageName}")
            )
        )
    } catch (e: ActivityNotFoundException) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("$URI_STORE$packageName")
            )
        )
    }
}

fun openMail(context: Context) = with(context) {
    val intent = Intent(Intent.ACTION_MAIN)
    intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
    intent.addCategory(Intent.CATEGORY_APP_EMAIL)
    findActivity().startActivity(intent)
}
