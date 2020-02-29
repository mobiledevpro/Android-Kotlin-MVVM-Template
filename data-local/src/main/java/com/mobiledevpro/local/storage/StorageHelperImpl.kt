package com.mobiledevpro.local.storage

import android.content.Context
import android.os.Environment

import java.io.File

/**
 * Storage Helper
 *
 *
 * Created by Dmitriy Chernysh
 *
 * http://androiddev.pro
 */

class StorageHelperImpl(appContext: Context) : StorageHelper {

    private var mDefaultAppFolder: File? = null

    init {
        val externalAppFolderPath = getExtAppFolder(appContext)
        mDefaultAppFolder = if (externalAppFolderPath != null) {
            externalAppFolderPath
        } else {
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
        }
        createAppFolder()
    }


    /**
     * Check SD card is available for read and write
     *
     * @return True - SD card is exist
     */
    val isExternalStorageAvailable: Boolean
        get() {
            val state = Environment.getExternalStorageState()

            return if (state == Environment.MEDIA_MOUNTED) {
                true
            } else if (state == Environment.MEDIA_MOUNTED || state == Environment.MEDIA_MOUNTED_READ_ONLY) {
                false
            } else {
                false
            }
        }

    /**
     * Create folder for the app
     */
    private fun createAppFolder(): Boolean {
        if (!mDefaultAppFolder!!.exists()) {
            if (!mDefaultAppFolder!!.mkdirs()) {
                return false
            }
        }
        return true
    }

    /**
     * Check is file exists
     *
     * @param fileUrl File url
     * @return True - file exists
     */
    fun isFileExists(fileUrl: String): Boolean {
        val file = File(fileUrl)
        return file.exists()
    }

    /**
     * Remove file by url
     *
     * @param fileUrl File url
     * @return True -  file is removed
     */
    override fun removeFile(fileUrl: String): Boolean {
        val file = File(fileUrl)
        return if (file.exists()) {
            file.delete()
        } else true
    }

    /**
     * Remove file
     *
     * @param file File
     * @return True - file is removed
     */
    override fun removeFile(file: File): Boolean {
        if (isExternalStorageAvailable) {
            if (file.exists()) {
                return file.delete()
            }
        }
        return false
    }

    /**
     * Remove non empty folder
     *
     * @param folderPath Folder path
     * @return True - Removed
     */
    fun removeNonEmptyFolder(folderPath: File): Boolean {

        if (folderPath.exists()) {
            for (file in folderPath.listFiles()) {
                if (file.isDirectory) {
                    removeNonEmptyFolder(file)
                } else {
                    file.delete()
                }
            }
        }

        return folderPath.delete()
    }

    /**
     * Get path to external App directory
     *
     * @param context Context
     * @return Folder
     */
    private fun getExtAppFolder(context: Context): File? {
        try {
            val extAppFolders = context.getExternalFilesDirs("") ?: return null

            for (file in extAppFolders) {
                if (file != null) {
                    //external storage has more priority than internal storage
                    if (!(file.absolutePath.contains("emulated") || file.absolutePath.contains("sdcard0"))) {
                        return file
                    }
                }
            }

            if (extAppFolders.size > 0) {
                val indexLastItem = extAppFolders.size - 1
                return if (extAppFolders[indexLastItem] != null) {
                    extAppFolders[indexLastItem]
                } else {
                    null
                }
            } else {
                return null
            }
        } catch (e: Exception) {
            return null
        }

    }

}
