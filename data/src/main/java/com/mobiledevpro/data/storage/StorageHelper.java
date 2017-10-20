package com.mobiledevpro.data.storage;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Storage Helper
 * <p>
 * Created by Dmitriy V. Chernysh on 14.10.16.
 * dmitriy.chernysh@gmail.com
 * <p>
 * www.mobile-dev.pro
 */

public class StorageHelper {

    private File mDefaultAppFolder;
    private static StorageHelper sStorageHelper;

    private StorageHelper(Context appContext) {
    }

    public static StorageHelper get(Context appContext) {
        if (sStorageHelper == null) {
            sStorageHelper = new StorageHelper(appContext);
        }
        sStorageHelper.initAppFolder(appContext);

        return sStorageHelper;
    }

    private void initAppFolder(Context appContext) {
        File externalAppFolderPath = getExtAppFolder(appContext);
        if (externalAppFolderPath != null) {
            mDefaultAppFolder = externalAppFolderPath;
        } else {
            mDefaultAppFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        }

        createAppFolder();
    }

    /**
     * Create folder for the app
     */
    private boolean createAppFolder() {
        if (!mDefaultAppFolder.exists()) {
            if (!mDefaultAppFolder.mkdirs()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check SD card is available for read and write
     *
     * @return True - SD card is exist
     */
    public boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();

        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else if (state.equals(Environment.MEDIA_MOUNTED) || state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            return false;
        } else {
            return false;
        }
    }

    /**
     * Check is file exists
     *
     * @param fileUrl File url
     * @return True - file exists
     */
    public boolean isFileExists(String fileUrl) {
        File file = new File(fileUrl);
        return file.exists();
    }

    /**
     * Remove file by url
     *
     * @param fileUrl File url
     * @return True -  file is removed
     */
    public boolean removeFile(String fileUrl) {
        File file = new File(fileUrl);
        if (file.exists()) {
            return file.delete();
        }
        return true;
    }

    /**
     * Remove file
     *
     * @param file File
     * @return True - file is removed
     */
    public boolean removeFile(File file) {
        if (isExternalStorageAvailable()) {
            if (file.exists()) {
                return file.delete();
            }
        }
        return false;
    }

    /**
     * Remove non empty folder
     *
     * @param folderPath Folder path
     * @return True - Removed
     */
    public boolean removeNonEmptyFolder(File folderPath) {

        if (folderPath.exists()) {
            for (File file : folderPath.listFiles()) {
                if (file.isDirectory()) {
                    removeNonEmptyFolder(file);
                } else {
                    file.delete();
                }
            }
        }

        return folderPath.delete();
    }

    /**
     * Get path to external App directory
     *
     * @param context Context
     * @return Folder
     */
    private File getExtAppFolder(Context context) {
        try {
            File[] extAppFolders = context.getExternalFilesDirs("");
            if (extAppFolders == null) return null;

            for (File file : extAppFolders) {
                if (file != null) {
                    //external storage has more priority than internal storage
                    if (!(file.getAbsolutePath().contains("emulated") || file.getAbsolutePath().contains("sdcard0"))) {
                        return file;
                    }
                }
            }

            if (extAppFolders.length > 0) {
                int indexLastItem = extAppFolders.length - 1;
                if (extAppFolders[indexLastItem] != null) {
                    return extAppFolders[indexLastItem];
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }

    }

}
