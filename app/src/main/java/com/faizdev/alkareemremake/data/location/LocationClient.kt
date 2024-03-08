package com.faizdev.alkareemremake.data.location

import android.location.Location
import kotlinx.coroutines.flow.Flow


interface LocationClient{
    fun getLocationUpdates(): Flow<LocationTrackerCondition<Location?>>
}