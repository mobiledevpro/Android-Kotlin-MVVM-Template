package com.mobiledevpro.rx.executor

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Rx scheduler for UI thread
 *
 *
 */
class UiMainThread : PostExecutionThread {
    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()
}