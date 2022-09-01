#! /bin/sh

#Token from Dropbox Settings.
#DROPBOX_TOKEN="add this token to Circle CI Environment Variables in the project settings"

APK_FILES="./app/build/outputs/bundle/*/*-release.aab"
MAPPING_FILES="./app/build/outputs/mapping/*/mapping.txt"

for FILE in $APK_FILES; do

  #check file is exist and not empty
  if [ -f "${FILE}" ] && [ -s "${FILE}" ]; then

    echo "Found AAB: ${FILE}"
    FILE_NAME=$(basename "${FILE}")

    curl -X POST https://content.dropboxapi.com/2/files/upload \
      --header "Authorization: Bearer ${DROPBOX_TOKEN}" \
      --header "Dropbox-API-Arg: {\"path\": \"\/${FILE_NAME}\",\"mode\": \"overwrite\",\"autorename\": true,\"mute\": false}" \
      --header "Content-Type: application/octet-stream" \
      --data-binary @"${FILE}"
  else
    echo "File ${FILE} is empty"
    exit 1
  fi

done

for TXT in $MAPPING_FILES; do

  #check file is exist and not empty
  if [ -f "${TXT}" ] && [ -s "${TXT}" ]; then

    echo "Found mapping: ${TXT}"
    TXT_NAME=$(basename "${TXT}")

    curl -X POST https://content.dropboxapi.com/2/files/upload \
      --header "Authorization: Bearer ${DROPBOX_TOKEN}" \
      --header "Dropbox-API-Arg: {\"path\": \"\/${TXT_NAME}\",\"mode\": \"overwrite\",\"autorename\": true,\"mute\": false}" \
      --header "Content-Type: application/octet-stream" \
      --data-binary @"${FILE}"
  else
    echo "File ${TXT} is empty"
    exit 1
  fi

done
