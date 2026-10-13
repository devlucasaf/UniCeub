# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/lucasfreitas/Library/Android/sdk/tools/proguard/proguard-android.txt

# Room Database
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-keepclassmembers @androidx.room.Entity class * { *; }
-keep @androidx.room.Dao class *

# Keep model classes
-keep class com.unitask.app.models.** { *; }

# Suppress notes about duplicate class definitions
-dontnote kotlin.**
-dontnote com.google.**
