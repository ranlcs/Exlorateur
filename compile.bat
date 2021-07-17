@echo off
aapt package -f -m -F bin/Explo.unaligned.apk -M AndroidManifest.xml -S res -I "C:\Users\ranlcs\AppData\Local\Android\Sdk\platforms\android-16\android.jar"
aapt add Explo.unaligned.apk classes.dex
aapt list Explo.unaligned.apk
keytool -genkeypair -validity 365 -keystore mykey.keystore -keyalg RSA -keysize 2048
zipalign -f 4 Explo.unaligned.apk Explo.apk
apksigner sign --ks mykey.keystore Explo.apk
cd..
@echo on
