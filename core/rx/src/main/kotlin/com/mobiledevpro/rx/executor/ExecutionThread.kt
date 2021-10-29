package com.mobiledevpro.rx.executor

import io.reactivex.Scheduler

interface ExecutionThread {
    val scheduler: Scheduler
}
