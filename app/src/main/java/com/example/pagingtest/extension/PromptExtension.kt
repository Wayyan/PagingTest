package com.example.pagingtest.extension

import android.content.Context
import android.view.LayoutInflater
import android.view.WindowManager
import com.example.pagingtest.databinding.DialogPromptBinding

fun Context.showPromptDialog(msg: String, btnText: String? = null, action: (() -> Unit)? = null) {
    val binding = DialogPromptBinding.inflate(LayoutInflater.from(this))
    val dialog = this.getDialog(binding.root, false)
    dialog.window?.setLayout(
        (this.getScreenWidth() * 0.9).toInt(),
        WindowManager.LayoutParams.WRAP_CONTENT
    )
    binding.tvPrompt.text = msg
    btnText?.let {
        binding.tvOk.text = it
    }
    binding.tvOk.setOnClickListener {
        action?.invoke()
        dialog.shouldDismiss()
    }
    dialog.shouldShow()
}