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
package com.mobiledevpro.domain.core.mapper

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.exceptions.CompositeException

/**
 * Converting results for presentation layer
 *
 * Created on Dec 15, 2020.
 *
 */
fun <T> Observable<T>.toViewResult(): Observable<RxResult<T>> = this
    .map<RxResult<T>> { RxResult.Success(it) }
    .onErrorReturn {
        RxResult.Failure(
            if (it is CompositeException) it.exceptions.last().toView()
            else it.toView()
        )
    }

fun <T> Single<T>.toViewResult(): Single<RxResult<T>> = this
    .map<RxResult<T>> { RxResult.Success(it) }
    .onErrorReturn {
        RxResult.Failure(
            if (it is CompositeException) it.exceptions.last().toView()
            else it.toView()
        )
    }


fun Completable.toViewResult(): Single<RxResult<None>> = this
    .toSingleDefault<RxResult<None>>(RxResult.Success(None()))
    .onErrorReturn {
        RxResult.Failure(
            if (it is CompositeException) it.exceptions.last().toView()
            else it.toView()
        )
    }

private fun Throwable.toView() = when (this) {
 /*   is DataNetworkConnectionThrowable -> NetworkConnectionThrowable(message)
    is DataNetworkThrowable -> NetworkThrowable(message)
    is YoutubeSignInCancelledThrowable -> this
    is DataYoutubeNetworkNoDefaultChannel -> YoutubeNetworkNoDefaultChannel()
    is DataYoutubeNetworkDefaultChannelNotLinked -> YoutubeNetworkDefaultChannelNotLinked()
    is DataYoutubeNetworkNoAccessToAccount -> YoutubeNetworkNoAccessToAccount(message)
    is DataLoginThrowable -> LoginThrowable(message)*/
    else -> Throwable(message)
}