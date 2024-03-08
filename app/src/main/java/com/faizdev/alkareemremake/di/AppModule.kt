package com.faizdev.alkareemremake.di


import android.app.Application
import android.content.Context
import android.health.connect.datatypes.ExerciseRoute.Location
import androidx.room.Room
import com.faizdev.alkareemremake.R
import com.faizdev.alkareemremake.data.location.LocationClient
import com.faizdev.alkareemremake.data.location.LocationClientImpl
import com.faizdev.alkareemremake.data.repository.QuranRepository
import com.faizdev.alkareemremake.data.repository.QuranRepositoryImpl
import com.faizdev.alkareemremake.data.source.local.BookmarkDatabase
import com.faizdev.alkareemremake.data.source.local.QoranDatabase
import com.faizdev.alkareemremake.data.source.service.ApiInterface
import com.faizdev.alkareemremake.service.MyPlayerService
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import snow.player.PlayerClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePlayer(
        @ApplicationContext context: Context
    ): PlayerClient {
        return PlayerClient.newInstance(context, MyPlayerService::class.java)
    }


    @Provides
    @Singleton
    fun provideBookmarkDatabase(
        @ApplicationContext context: Context
    ): BookmarkDatabase {
        return Room.databaseBuilder(
            context, BookmarkDatabase::class.java, "bookmark.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext applicationContext: Context
    ): QoranDatabase {
        return Room.databaseBuilder(
            applicationContext,
            QoranDatabase::class.java,
            "qoran.db"
        ).createFromInputStream {
            applicationContext.resources.openRawResource(R.raw.qoran)
        }.build()


    }

    @Provides
    @Singleton
    fun provideRepository(
        quranDatabase: QoranDatabase,
        apiInterface: ApiInterface,
        bookmarkDatabase: BookmarkDatabase
    ): QuranRepository {
        return QuranRepositoryImpl( quranDatabase, apiInterface, bookmarkDatabase)
    }

    @Provides
    @Singleton
    fun provideLocationClient(
        app : Application,
        coroutineScope: CoroutineScope
    ) : LocationClient {
        return LocationClientImpl(
            app,
            LocationServices.getFusedLocationProviderClient(app.applicationContext),
            coroutineScope
        )
    }

    @Provides
    @Singleton
    fun provideApi(
        @ApplicationContext applicationContext: Context
    ) : ApiInterface{
        return Retrofit.Builder()
            .baseUrl("https://prayer-times-xi.vercel.app/api/prayer/ ")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideCoroutineScope(
    ): CoroutineScope{
        return CoroutineScope(Dispatchers.IO)
    }
}

