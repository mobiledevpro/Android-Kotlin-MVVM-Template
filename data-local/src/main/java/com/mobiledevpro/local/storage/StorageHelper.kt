package com.mobiledevpro.local.storage

import java.io.File

/**
 * Device Storage Helper
 *
 *
 * Created by Dmitriy Chernysh on Feb 29, 2020.
 *
 *
 * http://androiddev.pro
 */
interface StorageHelper {
    fun removeFile(fileUrl: String): Boolean

    fun removeFile(file: File): Boolean
}