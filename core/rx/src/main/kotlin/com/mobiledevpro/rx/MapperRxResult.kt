/*
 * Copyright 2020 | Dmitri Chernysh | http://mobile-dev.pro
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

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.exceptions.CompositeException

/**
 * Converting results for presentation layer
 *
 *
 */
fun <T> Observable<T>.toViewResult(): Observable<RxResult<T>> =
    this
        .map<RxResult<T>> { RxResult.Success(it) }
        .onErrorReturn {
            it.toResultFailure()
        }

fun <T> Single<T>.toViewResult(): Single<RxResult<T>> =
    this
        .map<RxResult<T>> { RxResult.Success(it) }
        .onErrorReturn {
            it.toResultFailure()
        }


fun Completable.toViewResult(): Single<RxResult<None>> =
    this
        .toSingleDefault<RxResult<None>>(RxResult.Success(None()))
        .onErrorReturn {
            it.toResultFailure()
        }

private fun <T> Throwable.toResultFailure() =
    RxResult.Failure<T>(
        if (this is CompositeException) exceptions.last()
        else this
    )
