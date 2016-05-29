# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/shuaizhimin/Documents/Android/adt-bundle-mac-x86_64-20140702/sdk/tools/proguard/proguard-android.txt
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
# 压缩 优化 混淆 预检
#}
#
####################基本指令#####################################

#指定压缩级别 默认为5
-optimizationpasses 5
#混淆时不适用大小写混合,混淆后的类名小写
-dontusemixedcaseclassnames
#混淆公共类库
-dontskipnonpubliclibraryclasses
#不忽略公共库的类的成员
-dontskipnonpubliclibraryclassmembers
#不做预校验,可加快混淆速度
-dontpreverify
#混淆后生产映射文件 使用printmap 指定映射文件名称
-verbose
-printmapping proguardMapping.txt
#指定混淆时采用的算法，后面的参数是过滤器（谷歌推荐的算法，一般不改变）
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
#保护代码中的Annotation不被混淆  Json映射 如fastjson
-keepattributes *Annotation*
#避免混淆泛型 json实体映射
-keepattributes Signature
#抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable
-ignorewarnings
##########################need keep###############################
#保留本地native方法
-keepclasseswithmembernames class *{
   native <methods>;
}
#保留枚举类不被混淆
-keepclasseswithmembers enum *{
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
#保留自定义View 不被混淆
-keep public class * extends android.view.View{
   *** get*();
   void set*(***);
   public <init>(android.content.Context);
   public <init>(android.content.Context,android.util.AttributeSet);
   public <init>(android.content.Context,android.util.AttributeSet,int);
}
#保留Parcelabel 不被混淆
-keep class * implements android.os.Parcelable{
  public static final android.os.Parcelable$Creator *;
}
#保留Serializable 序列化不被混淆
-keepclassmembernames class * implements java.io.Serializable{
  static final long serialVersionUID;
  private static final java.io.ObjectStreamField[] serialPersistenFields;
  private void writeObject(java.io.ObjectOutputStream);
  private void readObject(java.io.ObjectInputStream);
  java.lang.Object writeReplace();
  java.lang.Object readResolve();
}
########################App#########################################
-keep class **.R$*{
     *;
}
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.app.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing,ILicensingService
-keepnames public class * extends android.support.v4.app.Fragment
-keep class android.support.v4.view.ViewPager.** {*;}
-keep class * extends android.support.v4.view.ViewPager{*;}
-keepattributes *JavascriptInterface*


#-keep class com.ai.eve.common.bean.** {*;}