/*
 * Copyright 2022 | Dmitri Chernysh | https://mobile-dev.pro
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.mobiledevpro.common.ui.coroutines

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Base UseCase with Coroutines
 *
 * Created on Sep 12, 2022.
 *
 */
abstract class BaseCoroutinesUseCase<Results, in Params>(
    executionDispatcher: CoroutineDispatcher
) : BaseUseCase(executionDispatcher) {

    abstract suspend fun buildUseCase(params: Params? = null): Results

    suspend fun execute(params: Params? = null): Result<Results> =
        withContext(dispatcher) {
            try {
                if (dispatcher == Dispatchers.Main)
                    throw RuntimeException("Use case '${this::class.simpleName}' cannot be executed in $dispatcher")

                resultOf {
                    this@BaseCoroutinesUseCase.buildUseCase(params)
                }
            } catch (e: Exception) {
                logException(e)
                Result.failure(Throwable(e.localizedMessage))
            }
        }

    override fun logException(e: Exception) {
        Log.e(this::class.simpleName, "${this::class.simpleName} : ${e.localizedMessage}")
    }
}