package com.boostcamp.mountainking.ui.tracking

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.IBinder
import android.util.Log

class LocationServiceManager(private val context: Context) {
    private lateinit var locationService : LocationService

    private var _isBound = false
    val isBound : Boolean get() = _isBound

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.i("LocationServiceManager", "onServiceConnected")
            val binder = service as LocationService.LocationBinder
            locationService = binder.getService()
            locationService.requestLocationUpdates()
            _isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            _isBound = false
        }

    }

    fun startService() {
        val intent = Intent(context, LocationService::class.java)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent)
        } else {
            context.startService(intent)
        }

    }

    fun bindService() {
        Intent(context, LocationService::class.java).also { intent ->
            context.bindService(intent, serviceConnection, 0)
        }
    }

    fun unBindService() {
        context.unbindService(serviceConnection)
        _isBound = false
    }

}