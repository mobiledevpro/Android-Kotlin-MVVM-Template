#! /bin/sh

# Toke from TestFairy profile settings.
# TESTFAIRY_API_KEY="add this token to Circle CI Environment Variables in the project settings"

APK_FILES="./app/build/outputs/apk/*/*/*-release.apk"

for FILE in $APK_FILES
do

   #check file is exist and not empty
   if [ -f "${FILE}" ] && [ -s "${FILE}" ]
     then
      echo "Found APK: ${FILE}"

      curl https://app.testfairy.com/api/upload \
        -F api_key="${TESTFAIRY_API_KEY}" \
        -F file=@"${FILE}" \
        -F format=readable \
        -F metrics='cpu,memory,network,network-requests,logcat' \
        -F max-duration="5h" \
        -F video-quality="medium" \
        -F video="on" \
        -F notify='off' \
        -F auto-update="off"
     else
       echo "File ${FILE} is empty"; exit 1
   fi

done
