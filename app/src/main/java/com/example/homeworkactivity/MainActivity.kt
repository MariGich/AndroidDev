package com.example.homeworkactivity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

val TAG = "GichMS"

class MainActivity : AppCompatActivity() {
    private val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if ("end" == intent.action) {
                val result = intent.getIntExtra("value", 0)
                Log.d(TAG, result.toString())
                val toast: Toast = Toast.makeText(
                    context,
                    "Число перестановок из кол-ва перезапуска activity: " + result.toString(),
                    Toast.LENGTH_LONG
                )
                toast.show()
            }
        }
    }
    private var N = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerReceiver(broadcastReceiver, IntentFilter("end"))

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("recreate_k", N)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        N = savedInstanceState.getInt("recreate_k") + 1
        Log.w(TAG, N.toString())
        startService(Intent(this, MyIntentService::class.java).putExtra("input", N))
    }
}