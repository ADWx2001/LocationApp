package com.example.locationapp

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class LocationUtil(var context: Context) {

    private val _fusedLocationClien:FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    fun requestLocationUpdates(viewModel: LocationViewModel){

    }
    
    fun hasLocationPermission(context: Context):Boolean{
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                &&
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }
}