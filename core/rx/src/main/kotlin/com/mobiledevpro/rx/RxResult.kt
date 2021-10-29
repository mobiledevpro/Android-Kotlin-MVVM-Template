/*
 * Copyright 2021 | Dmitri Chernysh | http://mobile-dev.pro
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
package com.mobiledevpro.rx

/**
 * Class to deliver results to presentation layer
 *
 * Created on Dec 15, 2020.
 *
 */
sealed class RxResult<T> {
    data class Success<T>(val data: T) : RxResult<T>()

    data class Failure<T>(val throwable: Throwable) : RxResult<T>()
}

class None