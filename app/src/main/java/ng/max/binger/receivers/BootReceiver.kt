package ng.max.binger.receivers

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import ng.max.binger.services.SyncService

class BootReceiver : BroadcastReceiver() {


    companion object {
        const val REQUEST_CODE = 1234
    }

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.

        val newIntent = Intent()
        newIntent.setClass(context, SyncService::class.java)
        context.startService(newIntent)
        //TODO "BootReceiver.onReceive() is not implemented"
    }
}
