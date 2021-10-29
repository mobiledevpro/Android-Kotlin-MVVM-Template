package com.mobiledevpro.rx.usecase


import com.mobiledevpro.rx.executor.ExecutionThread
import com.mobiledevpro.rx.executor.PostExecutionThread
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 *
 * By convention each UseCase implementation will return the result using a [DisposableObserver]
 * that will execute its job in a background thread and will post the result in the UI thread.
 *
 * This use case is to be used when we expect multiple values to be emitted via an [Observable].
 *
 *
 */
abstract class ObservableUseCase<Results, in Params>(
    executionThread: ExecutionThread,
    postExecutionThread: PostExecutionThread
) : BaseRxUseCase(executionThread, postExecutionThread) {

    /**
     * Builds an [Observable] which will be used when executing the current [ObservableUseCase].
     */
    abstract fun buildUseCaseObservable(params: Params? = null): Observable<Results>

    /**
     * Executes the current use case.
     */
    fun execute(observer: DisposableObserver<Results>, params: Params? = null) {
        val observable = this.buildUseCaseObservable(params)
            .subscribeOn(executionThreadScheduler)
            .observeOn(postExecutionThreadScheduler)
        addToDisposable(observable.subscribeWith(observer))
    }

    /**
     * Executes the current use case.
     */
    fun execute(params: Params? = null): Observable<Results> =
        this.buildUseCaseObservable(params)
            .subscribeOn(executionThreadScheduler)
            .observeOn(postExecutionThreadScheduler)

}