@echo off
aapt package -f -m -J src -M AndroidManifest.xml -S res -I "C:\Users\ranlcs\AppData\Local\Android\Sdk\platforms\android-16\android.jar"
javac -d obj -cp src -bootclasspath "C:\Users\ranlcs\AppData\Local\Android\Sdk\platforms\android-16\android.jar" src/com/lcs/Explo/*.java
dx --dex --output=bin/classes.dex obj
@echo on
