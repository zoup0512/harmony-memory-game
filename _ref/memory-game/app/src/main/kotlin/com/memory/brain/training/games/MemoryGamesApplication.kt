package com.memory.brain.training.games

import android.app.Application
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class MemoryGamesApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: androidx.work.WorkerFactory

    override fun onCreate() {
        super.onCreate()
        
        // Initialize Timber for logging
        Timber.plant(Timber.DebugTree())
        
        // TODO: Initialize Firebase when google-services.json is added
        // FirebaseApp.initializeApp(this)
        
        // Facebook SDK initializes automatically via ContentProvider
        // No need to call FacebookSdk.sdkInitialize() manually
        
        Timber.d("MemoryGamesApplication initialized")
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
}
