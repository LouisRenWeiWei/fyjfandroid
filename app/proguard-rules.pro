# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\software\java\sdk/tools/proguard/proguard-android.txt
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
-optimizationpasses 7  #指定代码的压缩级别 0 - 7
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*  #淆采用的算法
#google default
# This is a configuration file for ProGuard.
# http://proguard.sourceforge.net/index.html#manual/usage.html

# 混合时不使用大小写混合，混合后的类名为小写
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose
-ignorewarnings

# Optimization is turned off by default. Dex does not like code run
# through the ProGuard optimize and preverify steps (and performs some
# of these optimizations on its own).
-dontoptimize
-dontpreverify
# Note that if you want to enable optimization, you cannot just
# include optimization flags in your own project configuration file;
# instead you will need to point to the
# "proguard-android-optimize.txt" file instead of this one from your
# project.properties file.

-keepattributes *Annotation*
-keep public class com.google.vending.licensing.ILicensingService
-keep public class com.android.vending.licensing.ILicensingService

# For native methods, see http://proguard.sourceforge.net/manual/examples.html#native
-keepclasseswithmembernames class * {
    native <methods>;
}

# keep setters in Views so that animations can still work.
# see http://proguard.sourceforge.net/manual/examples.html#beans
-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}

# We want to keep methods in Activity that could be used in the XML attribute onClick
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

# For enumeration classes, see http://proguard.sourceforge.net/manual/examples.html#enumerations
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keepclassmembers class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator CREATOR;
}

-keepclassmembers class **.R$* {
    public static <fields>;
}

# The support library contains references to newer platform versions.
# Don't warn about those in case this app is linking against an older
# platform version.  We know about them, and they are safe.
-dontwarn android.support.**

# Understand the @Keep support annotation.
-keep class android.support.annotation.Keep

-keep @android.support.annotation.Keep class * {*;}

-keepclasseswithmembers class * {
    @android.support.annotation.Keep <methods>;
}

-keepclasseswithmembers class * {
    @android.support.annotation.Keep <fields>;
}

-keepclasseswithmembers class * {
    @android.support.annotation.Keep <init>(...);
}
#保持自定义控件类不被混淆，指定格式的构造方法不去混淆
-keepclasseswithmembers class * {
    public <init>(android.content.Context);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

#--------------------------------------------------------------
#移除log 另外的一种实现方案是通过BuildConfig.DEBUG的变量来控制
-assumenosideeffects class android.util.Log {
    public static *** v(...);
    public static *** i(...);
    public static *** d(...);
    public static *** w(...);
    public static *** e(...);
}


-keep class * implements android.os.Parcelable {  #保持 Parcelable 不被混淆（aidl文件不能去混淆）
    public static final android.os.Parcelable$Creator *;
}

-keepnames class * implements java.io.Serializable #需要序列化和反序列化的类不能被混淆（注：Java反射用到的类也不能被混淆）

-keepclassmembers class * implements java.io.Serializable { #保护实现接口Serializable的类中，指定规则的类成员不被混淆
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

-keepattributes Signature  #过滤泛型（不写可能会出现类型转换错误，一般情况把这个加上就是了）

-keep class **.R$* { *; }  #保持R文件不被混淆，否则，你的反射是获取不到资源id的

# js 交互
-keep class * extends com.wwren.all.widget.SimpleWebView.JavascriptObject { *; }  #保护WebView对HTML页面的API不被混淆
-keepclassmembers class * extends android.webkit.WebViewClient {  #如果你的项目中用到了webview的复杂操作 ，最好加入
     public void *(android.webkit.WebView,java.lang.String,android.graphics.Bitmap);
     public boolean *(android.webkit.WebView,java.lang.String);
}
-keepclassmembers class * extends android.webkit.WebChromeClient {  #如果你的项目中用到了webview的复杂操作 ，最好加入
     public void *(android.webkit.WebView,java.lang.String);
}
#对WebView的简单说明下：经过实战检验,做腾讯QQ登录，如果引用他们提供的jar，若不加防止WebChromeClient混淆的代码，oauth认证无法回调，反编译基代码后可看到他们有用到WebChromeClient，加入此代码即可。


-keep class com.wwren.all.entity.** { *; }  #转换JSON的JavaBean，类成员名称保护，使其不被混淆


-keep class com.wwren.all.widget.** { *; }

##################################################################
# 下面都是项目中引入的第三方 jar 包。第三方 jar 包中的代码不是我们的目标和关心的对象，故而对此我们全部忽略不进行混淆。
# 第三方jar包中如果有.so文件，不用去理会，引入的第三方jar文件不要混淆，否则可能会报异常
##################################################################
-dontwarn android.support.**
-keep class android.** {*;}
-dontwarn android.**
-keep class android.support.** { *; }
-keep interface android.support.** { *; }
-keep public class * extends android.support.**
-keep public class * extends android.app.Activity  #所有activity的子类不要去混淆
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService #指定具体类不要去混淆
-keep public class * extends android.support.v4.app.Fragment

-keep class sun.misc.Unsafe { *; }
-keep class org.json.** { *; }

#-libraryjars libs/alipaySdk-20151215.jar
-dontwarn com.aplipay.**
-keep class com.aplipay.** { *; }
-dontwarn com.ta.utdid2.**
-keep class com.ta.utdid2.** { *; }
-dontwarn com.ut.device.**
-keep class com.ut.device.** { *; }
-dontwarn org.json.alipay.**
-keep class org.json.alipay.** { *; }

#-libraryjars libs/bolts-android-1.1.4.jar
-dontwarn bolts.**
-keep class bolts.** { *; }

#-libraryjars libs/libapshare.jar
-dontwarn com.alipay.share.sdk.**
-keep class com.alipay.share.sdk.** { *; }

#-libraryjars libs/mta-sdk-1.6.2.jar
#-libraryjars libs/open_sdk_r5756.jar
#-libraryjars libs/SocialSDK_WeiXin_1.jar
-dontwarn com.tencent.**
-keep class com.tencent.** { *; }

#-libraryjars libs/SocialSDK_QQZone_3.jar
#-libraryjars libs/SocialSDK_Sina.jar
#-libraryjars libs/SocialSDK_WeiXin_2.jar
#-libraryjars libs/umeng-analytics-v6.0.1.jar
#-libraryjars libs/umeng_social_sdk.jar
-dontwarn com.umeng.**
-keep class com.umeng.** { *; }

#-libraryjars libs/weiboSDKCore_3.1.4.jar
-dontwarn com.sina.**
-keep class com.sina.** { *; }


#butterknife
-dontwarn butterknife.**
-keep class butterknife.** { *; }

#nineoldandroids
-dontwarn com.nineoldandroids.**
-keep class com.nineoldandroids.** { *; }


#imageloader
-dontwarn com.nostra13.**
-keep class com.nostra13.** { *; }


#eventbus  -- greendao
-dontwarn org.greenrobot.**
-keep class org.greenrobot.** { *; }
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

#greendao
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
    public static final java.lang.String TABLENAME;
}
-keep class **$Properties

#org.apache
-dontwarn org.apache.**
-keep class org.apache.** { *; }


#com.squareup.haha
-dontwarn com.squareup.**
-keep class com.squareup.** { *; }

#utils
-keep class com.blankj.utilcode.** { *; }
-keepclassmembers class com.blankj.utilcode.** { *; }
-dontwarn com.blankj.utilcode.**

#umeng
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}


-keepclasseswithmembers class * {
    @com.android.volley.ext.ResponseSuccess <methods>;
    @com.android.volley.ext.ResponseError <methods>;
}

