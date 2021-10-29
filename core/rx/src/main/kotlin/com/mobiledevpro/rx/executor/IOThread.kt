package com.mobiledevpro.rx.executor

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * Rx scheduler for IO thread
 *
 */
class IOThread : ExecutionThread {
    override val scheduler: Scheduler
        get() = Schedulers.io()
}