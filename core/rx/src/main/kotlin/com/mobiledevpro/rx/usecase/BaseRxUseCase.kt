package com.mobiledevpro.rx.usecase

import com.mobiledevpro.rx.executor.ExecutionThread
import com.mobiledevpro.rx.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseRxUseCase(
    executionThread: ExecutionThread,
    postExecutionThread: PostExecutionThread
) {

    protected val executionThreadScheduler: Scheduler = executionThread.scheduler

    protected val postExecutionThreadScheduler: Scheduler = postExecutionThread.scheduler

    private val disposables = CompositeDisposable()

    open fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    protected fun addToDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}
