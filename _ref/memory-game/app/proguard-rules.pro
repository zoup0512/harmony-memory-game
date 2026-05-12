# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.

# Keep Kotlin metadata
-keep class kotlin.Metadata { *; }

# Keep Hilt generated classes
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }
-keep class * extends dagger.hilt.android.internal.managers.ViewComponentManager$FragmentContextWrapper { *; }

# Keep Room
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-dontwarn androidx.room.paging.**

# Keep Retrofit
-keepattributes Signature
-keepattributes Exceptions
-keepattributes *Annotation*
-keep class retrofit2.** { *; }
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

# Keep Gson
-keep class com.google.gson.** { *; }
-keep class * implements com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# Keep data classes
-keep class com.memory.brain.training.games.domain.model.** { *; }
-keep class com.memory.brain.training.games.data.local.database.entity.** { *; }

# Keep Compose
-keep class androidx.compose.** { *; }
-dontwarn androidx.compose.**

# Keep Coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepclassmembers class kotlinx.coroutines.** {
    volatile <fields>;
}

# Keep Firebase
-keep class com.google.firebase.** { *; }
-keep class com.google.android.gms.** { *; }

# Keep Facebook SDK
-keep class com.facebook.** { *; }
-keepattributes Signature

# General
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile
