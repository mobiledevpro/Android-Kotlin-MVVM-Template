# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /home/cdv/Dev/Tools/android-sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Remove all logs
-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int i(...);
    public static int w(...);
    public static int d(...);
    #public static int e(...);
}

# Keep EventBus
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

#Crashlytics
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable
-keep public class * extends java.lang.Exception

#keep retrofit2
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on RoboVM on iOS. Will not be used at runtime.
-dontnote retrofit2.Platform$IOS$MainThreadExecutor
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions


-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
-dontwarn okio.**

#keep gson
-keep class com.google.gson.** { *; }

#keep Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder {
  *** rewind();
}

#keep searchview
-keep class android.support.v7.widget.SearchView { *; }

#keep Spinner
-keep class android.widget.ThemedSpinnerAdapter { *; }

 #keep fragments transition
-keep class android.support.v4.app.FragmentTransitionCompat21 { *; }

-keep class com.android.graphics.drawable.** { *; }

#THIS IS DOESN`T WORK :( keep this, because Vector animations doesn`t work with minifying on api < 21
-keep class android.support.graphics.drawable.** { *; }
# keep setters in VectorDrawables so that animations can still work.
-keepclassmembers class android.support.graphics.drawable.VectorDrawableCompat$* {
   void set*(***);
   *** get*();
}