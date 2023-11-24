-ignorewarnings
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose

-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

-dontwarn android.support.v4.**
-dontwarn android.os.**
-keep public class * extends android.app.Fragment

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View

# 保留support下的所有类及其内部类
-keep class android.support.** {*;}

# 保留继承的
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.v7.**
-keep public class * extends android.support.annotation.**

# 保留R下面的资源
-keep class **.R$* {*;}

#第三方不混淆
#-libraryjars /libs/*****.jar
-keep class com.king.view.arcseekbar.**{*;}
-keep class me.jessyan.autosize.**{*;}
-keep class com.bumptech.**{*;}
-keep class net.lingala.zip4j.**{*;}
-keep class com.tencent.**{*;}
-keep class org.xutils.**{*;}
-keep class pub.devrel.**{*;}
-keep class com.alibaba.**{*;}
-keep class com.afollestad.**{*;}
-keep class com.blankj.**{*;}
-keep class com.gyf.**{*;}
-keep class com.jakewharton.**{*;}
-keep class org.greenrobot.**{*;}

-keep class com.ljx.**{*;}
-keep class com.squareup.**{*;}
-keep class io.reactivex.**{*;}
-keep class com.aspsine.**{*;}
-keep class com.bumptech.**{*;}
-keep class com.chad.**{*;}
-keep class com.king.**{*;}

-keep class retrofit2.converter.**{*;}
-keep class com.king.**{*;}
-keep class com.king.**{*;}
-keep class com.king.**{*;}
-keep class com.nemo.msgsdk.**{*;}
-keep class com.adapter.**{*;}
-keep class com.nkr.**{*;}

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

#保留我们的自定义控件不被混淆
-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
   public <init>(android.content.Context);
   public <init>(android.content.Context, android.util.AttributeSet);
   public <init>(android.content.Context, android.util.AttributeSet, int);
}

#这样一来我们在layout中写的onClick都不会被影响
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

#保留枚举类不被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

#保留Parcelable序列化类不被混淆
-keepclassmembers class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator CREATOR;
}
# 保留Serializable序列化的类不被混淆
-keepnames class * implements java.io.Serializable
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
# 对于带有回调函数的onXXEvent、**On*Listener的，不能被混淆
-keepclassmembers class * {
    void *(**On*Event);
    void *(**On*Listener);
}

-keepclassmembers class **.R$* {
    public static <fields>;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

#gson
#如果用用到Gson解析包的，直接添加下面这几行就能成功混淆，不然会报错。
-keepattributes Signature
# Gson specific classes
-dontwarn sun.misc.**
# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.** { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.examples.android.model.** { <fields>; }
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}

# Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}