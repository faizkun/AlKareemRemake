package com.faizdev.alkareemremake.screen.adzanschedule

import android.Manifest
import android.location.Geocoder
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.faizdev.alkareemremake.R
import com.faizdev.alkareemremake.data.source.model.AdzanScheduleResponse
import com.faizdev.alkareemremake.screen.adzanschedule.state.AdzanScheduleState
import com.faizdev.alkareemremake.screen.adzanschedule.state.ErrorType
import com.faizdev.alkareemremake.ui.theme.poppins
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.util.Locale
import java.util.Locale.getDefault

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Destination
@Composable
fun AdzanScheduleScreen(
    viewModel: AdzanScheduleViewModel = hiltViewModel(),
    navigator: DestinationsNavigator,
) {


    val context = LocalContext.current
    val locationPermissions = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION
        )
    )

    if (!locationPermissions.allPermissionsGranted) {
        LaunchedEffect(true) {
            locationPermissions.launchMultiplePermissionRequest()
        }
    }

    LaunchedEffect(locationPermissions.allPermissionsGranted) {
        if (locationPermissions.allPermissionsGranted) {
            viewModel.getLocationUpdates()
        }
    }


    Scaffold(

        topBar = {
            TopAppBar(navigationIcon = {
                IconButton(onClick = {
                    navigator.navigateUp()
                }) {

                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "",
                        modifier = Modifier.size(55.dp)

                    )

                }
            },

                title = {
                    Text(
                        text = "",
                    )

                })
        }


    ) {

        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(89.dp)
                    .padding(start = 16.dp, end = 16.dp),
                shape = RoundedCornerShape(21.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xff47629E)),


                ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Row(
                        modifier = Modifier.padding(start = 16.dp, top = 15.dp)
                    ) {
                        Icon(
                            tint = Color.White,
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = ""
                        )

                        viewModel.currentLocation.collectAsState().let { currentLocation ->
                            val address = Geocoder(
                                context, getDefault()
                            ).getFromLocation(
                                currentLocation.value.latitude,
                                currentLocation.value.longitude,
                                1,
                            )
                            if (address?.isNotEmpty() == true) {
                                val locality = address.first()!!.locality
                                val subLocality = address.first()!!.subLocality
                                val subAdminArea = address.first()!!.subAdminArea
                                Text(
                                    modifier = Modifier.padding(start = 5.dp),
                                    text = "$locality, $subLocality, $subAdminArea",
                                    color = Color.White,
                                    fontSize = 14.sp,
                                    fontFamily = poppins
                                )
                            }

                        }

                    }


                }

//                    Text(
//                        modifier = Modifier
//                            .padding(start = 18.dp, top = 20.dp),
//                        text = "06:30",
//                        color = Color.White,
//                        fontSize = 29.sp,
//                        fontWeight = FontWeight.SemiBold,
//                        fontFamily = poppins,
//                    )


            }

            viewModel.adzanScheduleState.collectAsState(initial = AdzanScheduleState.Idle)
                .let { state ->
                    when (val event = state.value) {
                        is AdzanScheduleState.Error -> {
                            Box(
                                modifier = Modifier.fillMaxSize()
                            ) {

                                Column(
                                    modifier = Modifier.align(Alignment.Center),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = when (event.errorType) {
                                            ErrorType.NO_GPS -> ""
                                            ErrorType.PERMISSION_ERROR -> ""
                                            ErrorType.OTHERS -> ""
                                        },
                                        textAlign = TextAlign.Center,
                                        style = MaterialTheme.typography.titleLarge
                                    )
                                    Spacer(modifier = Modifier.height(12.dp))
                                    Button(onClick = { viewModel.getLocationUpdates() }) {
                                        Text(text = "Retry")
                                    }
                                }
                            }
                        }

                        AdzanScheduleState.Idle -> {
                            Box(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Column(
                                    modifier = Modifier.align(Alignment.Center),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "",
                                        style = MaterialTheme.typography.titleMedium,
                                        textAlign = TextAlign.Center
                                    )
                                    Spacer(modifier = Modifier.height(24.dp))
                                    CircularProgressIndicator()
                                }
                            }
                        }

                        is AdzanScheduleState.Success -> {
                            val sholatTime = event.data
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(64.dp)
                                    .padding(start = 16.dp, end = 16.dp, top = 13.dp),
                                shape = RoundedCornerShape(17.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0XFF89ADFF)),


                                ) {

                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(start = 16.dp)
                                        .weight(1f), verticalAlignment = Alignment.CenterVertically

                                ) {
                                    Text(
                                        text = "Shubuh",

                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        fontFamily = poppins
                                    )
                                    Column(
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text(
                                            text = sholatTime.fajr,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Thin,
                                            fontFamily = poppins,
                                            modifier = Modifier
                                                .align(Alignment.End)
                                                .padding(end = 16.dp)
                                        )
                                    }
                                }

                            }

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(64.dp)
                                    .padding(start = 16.dp, end = 16.dp, top = 13.dp),
                                shape = RoundedCornerShape(17.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0XFF89ADFF)),


                                ) {

                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(start = 16.dp)
                                        .weight(1f), verticalAlignment = Alignment.CenterVertically

                                ) {
                                    Text(
                                        text = "Dzuhur",

                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        fontFamily = poppins
                                    )
                                    Column(
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text(
                                            text = sholatTime.dhuhr,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Thin,
                                            fontFamily = poppins,
                                            modifier = Modifier
                                                .align(Alignment.End)
                                                .padding(end = 16.dp)
                                        )
                                    }
                                }

                            }

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(64.dp)
                                    .padding(start = 16.dp, end = 16.dp, top = 13.dp),
                                shape = RoundedCornerShape(17.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0XFF89ADFF)),


                                ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(start = 16.dp)
                                        .weight(1f), verticalAlignment = Alignment.CenterVertically

                                ) {
                                    Text(
                                        text = "Ashar",

                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        fontFamily = poppins
                                    )
                                    Column(
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text(
                                            text = sholatTime.asr,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Thin,
                                            fontFamily = poppins,
                                            modifier = Modifier
                                                .align(Alignment.End)
                                                .padding(end = 16.dp)
                                        )
                                    }
                                }

                            }

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(64.dp)
                                    .padding(start = 16.dp, end = 16.dp, top = 13.dp),
                                shape = RoundedCornerShape(17.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0XFF89ADFF)),


                                ) {

                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(start = 16.dp)
                                        .weight(1f), verticalAlignment = Alignment.CenterVertically

                                ) {
                                    Text(
                                        text = "Maghrib",

                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        fontFamily = poppins
                                    )
                                    Column(
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text(
                                            text = sholatTime.maghrib,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Thin,
                                            fontFamily = poppins,
                                            modifier = Modifier
                                                .align(Alignment.End)
                                                .padding(end = 16.dp)
                                        )
                                    }
                                }

                            }

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(64.dp)
                                    .padding(start = 16.dp, end = 16.dp, top = 13.dp),
                                shape = RoundedCornerShape(17.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0XFF89ADFF)),


                                ) {

                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(start = 16.dp)
                                        .weight(1f), verticalAlignment = Alignment.CenterVertically

                                ) {
                                    Text(
                                        text = "Isya",

                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        fontFamily = poppins
                                    )
                                    Column(
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text(
                                            text = sholatTime.isha,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Thin,
                                            fontFamily = poppins,
                                            modifier = Modifier
                                                .align(Alignment.End)
                                                .padding(end = 16.dp)
                                        )
                                    }
                                }

                            }

                        }

                    }
                }
        }

    }
}


//@Preview
//@Composable
//fun Pv() {
//    AdzanScheduleScreen()
//}




