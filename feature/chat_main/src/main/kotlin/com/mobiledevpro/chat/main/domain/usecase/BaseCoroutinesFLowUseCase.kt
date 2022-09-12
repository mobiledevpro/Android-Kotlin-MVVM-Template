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

import com.mobiledevpro.common.ui.coroutines.resultOf
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
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

    abstract fun buildUseCase(params: Params? = null): Flow<Results>

    fun execute(params: Params? = null): Flow<Result<Results>> =
        this.buildUseCase(params)
            .flowOn(dispatcher)
            .map {
                resultOf { it }
            }
}