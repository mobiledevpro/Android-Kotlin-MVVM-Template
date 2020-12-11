package com.mobiledevpro.app

import android.content.Context
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CheckKoinModulesTest : KoinTest {
    @Mock
    private lateinit var mockContext: Context


    @Test
    fun checkAllModules() = checkModules {

        androidContext(mockContext)
      //  modules(listOf(dataLocalModule))

    }
}