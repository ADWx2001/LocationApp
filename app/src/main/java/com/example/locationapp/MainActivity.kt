 package com.example.locationapp

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.locationapp.ui.theme.LocationAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel : LocationViewModel = viewModel()
            LocationAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp(viewModel)
                }
            }
        }
    }
}

 @Composable
 fun MyApp(viewModel: ViewModel){
     val context = LocalContext.current
     val locationUtils = LocationUtil(context)
     LocationDisplay(locationUtil = locationUtils, viewModel ,context = context)
 }

@Composable
 fun LocationDisplay(locationUtil: LocationUtil, viewModel: ViewModel, context: Context){

     val requestPermissionLauncher = rememberLauncherForActivityResult(
         contract = ActivityResultContracts.RequestMultiplePermissions() ,
         onResult = {
             permissions ->
             if(permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true
                 && permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true){
                 //I have permission
             }else{
                val rationalRequired = ActivityCompat.shouldShowRequestPermissionRationale(
                    context as MainActivity,
                    Manifest.permission.ACCESS_FINE_LOCATION) || ActivityCompat.shouldShowRequestPermissionRationale(
                    context as MainActivity,
                    Manifest.permission.ACCESS_COARSE_LOCATION)

                 if (rationalRequired){
                     Toast.makeText(context,"Location permission is required for this feature", Toast.LENGTH_LONG).show()
                 }else{
                     Toast.makeText(context,"Location permission is required make settings in android", Toast.LENGTH_LONG).show()
                 }
             }


         })

     Column (
         modifier = Modifier.fillMaxSize(),
         horizontalAlignment = Alignment.CenterHorizontally,
         verticalArrangement = Arrangement.Center){

         Button(onClick = {
             if(locationUtil.hasLocationPermission(context)){
                 //permission already have get the location
             }else{
                 //location doesn't have get the access for the location
                 requestPermissionLauncher.launch(
                     arrayOf(
                         Manifest.permission.ACCESS_FINE_LOCATION,
                         Manifest.permission.ACCESS_COARSE_LOCATION
                     )
                 )
             }
         }) {
             Text(text = "Get the location")
         }

     }
 }