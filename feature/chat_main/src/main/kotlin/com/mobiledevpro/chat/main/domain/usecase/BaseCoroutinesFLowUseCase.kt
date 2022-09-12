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
package com.mobiledevpro.chat.main.domain.usecase

import android.util.Log
import com.mobiledevpro.common.ui.coroutines.resultOf
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

/**
 * Base UseCase for Coroutines Flow result
 *
 * Created on Sep 12, 2022.
 *
 */
abstract class BaseCoroutinesFLowUseCase<Results, in Params>(
    executionDispatcher: CoroutineDispatcher
) : BaseUseCase(executionDispatcher) {

    abstract fun buildUseCaseFlow(params: Params? = null): Flow<Results>

    fun execute(params: Params? = null): Flow<Result<Results>> =
        try {
            this.buildUseCaseFlow(params)
                .flowOn(dispatcher)
                .map {
                    resultOf { it }
                }
        } catch (e: Exception) {
            Log.e("app.debug", "${this}.execute: ${e.localizedMessage}", e)
            logException(e)
            flowOf(Result.failure(Throwable(e.localizedMessage)))
        }

    override fun logException(e: Exception) {
        Log.e(this::class.simpleName, "${this::class.simpleName} : ${e.localizedMessage}")
    }
}