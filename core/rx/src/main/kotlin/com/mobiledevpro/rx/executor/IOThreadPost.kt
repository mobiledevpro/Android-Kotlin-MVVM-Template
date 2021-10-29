package com.mobiledevpro.rx.executor

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * Rx scheduler for IO thread
 *
 *
 */
class IOThreadPost : PostExecutionThread {
    override val scheduler: Scheduler
        get() = Schedulers.io()
}