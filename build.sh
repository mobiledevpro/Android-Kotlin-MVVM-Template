#! /bin/sh

echo y | android update sdk --filter "build-tools-30.0.4,android-33,extra-android-m2repository" --no-ui -a # Grab the Android Support Repo which isn't included in the container
mkdir "${ANDROID_HOME}/licenses" || true
echo "8933bad161af4178b1185d1a37fbf41ea5269c55" > "${ANDROID_HOME}/licenses/android-sdk-license"

#clone remote modules
git submodule init
git submodule update

#build dependencies
./gradlew dependencies

#read variables from the file (key pass, key secret, secret passphrase)
. './keystore.properties' &&
  echo "$KEYSTORE_SECRET" >app/release.asc &&
  gpg -d --passphrase "$KEYSTORE_SECRET_PASSPHRASE" --batch app/release.asc >app/release.jks &&
  rm app/release.asc &&

  # Export these variables to be able to use it in build.gradle signingConfigs
  export KEYPWD &&
  export KSTOREPWD &&
  export KEYSTORE_ALIAS &&

  #build AAB bundles
  ./gradlew clean bundleProductionRelease --stacktrace

rm app/release.jks
