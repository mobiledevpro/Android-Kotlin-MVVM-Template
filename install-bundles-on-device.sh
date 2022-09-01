#! /bin/sh
#
# Script to install AAB on connected device.
#
# Before continue please make sure
# you have the following variables in the file keystore.properties:
#
# KEYPWD - it's keyPassword
# KSTOREPWD - it's storePassword
# KEYSTORE_ALIAS - alias
# KEYSTORE_SECRET - it's a result of command 'gpg -c --armor release.keystore'
# KEYSTORE_SECRET_PASSPHRASE - it's a phrase to TAXI_KEYSTORE_SECRET
#

#read variables from the file (key pass, key secret, secret passphrase)
. './keystore.properties' &&
  echo "$KEYSTORE_SECRET" >app/release.asc &&
  gpg -d --passphrase "$KEYSTORE_SECRET_PASSPHRASE" --batch app/release.asc >app/release.jks &&
  rm app/release.asc &&

  # Export these variables to be able to use it in build.gradle signingConfigs
  export KEYPWD &&
  export KSTOREPWD &&
  export KEYSTORE_ALIAS &&
  # ./gradlew clean bundleRelease &&
  AAB_FILES="./app/build/outputs/bundle/*/*-release.aab"
KEY_STORE_FILE_PATH="app/release.jks"
BUNDLE_TOOL_PATH="/home/dmitri/Projects/bundletool-all-1.2.0.jar"

for FILE in $AAB_FILES; do
  echo "Found AAB: ${FILE}"
  FILE_NAME=$(basename ${FILE})
  DIR_PATH=$(dirname ${FILE})

  #Remove existing APKs before creating a new one
  rm -f ${DIR_PATH}/${FILE_NAME}.apks &&

    # Build APK set from bundles, for specific connected device
    java -jar ${BUNDLE_TOOL_PATH} \
      build-apks \
      --overwrite \
      --connected-device \
      --bundle=$FILE \
      --output=${DIR_PATH}/${FILE_NAME}.apks \
      --ks=$KEY_STORE_FILE_PATH \
      --ks-pass=pass:$KSTOREPWD \
      --ks-key-alias=$KEYSTORE_ALIAS \
      --key-pass=pass:$KEYPWD &&

    # Install APKs on connected device
    java -jar ${BUNDLE_TOOL_PATH} \
      install-apks \
      --apks=${DIR_PATH}/${FILE_NAME}.apks

done &&
  rm app/release.jks
