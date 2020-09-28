package com.example.homeworkactivity

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyIntentService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        sendBroadcast(
            Intent("end").putExtra(
                "value",
                factorial(intent?.getIntExtra("input", 0) ?: 0)
            )
        )
        return super.onStartCommand(intent, flags, startId)
    }

    private fun factorial(n: Int): Int {
        if (n >= 1)
            return n * factorial(n - 1)
        else
            return 1
    }
}