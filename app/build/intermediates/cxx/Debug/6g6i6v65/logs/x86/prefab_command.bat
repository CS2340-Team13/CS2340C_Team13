@echo off
"C:\\Program Files\\Android\\Android Studio\\jbr\\bin\\java" ^
  --class-path ^
  "C:\\Users\\etaw2\\.gradle\\caches\\modules-2\\files-2.1\\com.google.prefab\\cli\\2.0.0\\f2702b5ca13df54e3ca92f29d6b403fb6285d8df\\cli-2.0.0-all.jar" ^
  com.google.prefab.cli.AppKt ^
  --build-system ^
  cmake ^
  --platform ^
  android ^
  --abi ^
  x86 ^
  --os-version ^
  33 ^
  --stl ^
  c++_static ^
  --ndk-version ^
  25 ^
  --output ^
  "C:\\Users\\etaw2\\AppData\\Local\\Temp\\agp-prefab-staging5774094327348556247\\staged-cli-output" ^
  "C:\\Users\\etaw2\\.gradle\\caches\\transforms-3\\0197bca8ae2cd7a969a5c0e567549a51\\transformed\\games-activity-1.2.2\\prefab"
