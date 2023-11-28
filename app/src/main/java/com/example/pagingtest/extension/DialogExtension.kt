package com.example.pagingtest.extension

import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window

fun Context.getDialog(view: View, cancelable: Boolean): Dialog {
    val dialog = Dialog(this)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog.setContentView(view)
    dialog.setCancelable(cancelable)
    return dialog
}

fun Context.getScreenWidth(): Int {
    return Resources.getSystem().displayMetrics.widthPixels
}

fun Dialog?.shouldDismiss() {
    if (this != null && this.isShowing)
        this.dismiss()
}

fun Dialog?.shouldShow() {
    if (this != null && !this.isShowing)
        this.show()
}