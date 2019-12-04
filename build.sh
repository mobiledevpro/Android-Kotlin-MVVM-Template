#! /bin/sh

echo y | android update sdk --filter "build-tools-29.0.2,android-29,extra-android-m2repository" --no-ui -a # Grab the Android Support Repo which isn't included in the container
mkdir "${ANDROID_HOME}/licenses" || true
echo "8933bad161af4178b1185d1a37fbf41ea5269c55" > "${ANDROID_HOME}/licenses/android-sdk-license"

#clone remote modules
git submodule init
git submodule update

#build dependencies
./gradlew dependencies

#build debug and release apk
./gradlew assemble