package com.mobiledevpro.chat.main.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.mobiledevpro.app.helper.ResourcesProvider
import com.mobiledevpro.chat.core.view.mapper.toRecyclerView
import com.mobiledevpro.chat.core.view.recycler.RecyclerItem
import com.mobiledevpro.chat.core.view.recycler.RecyclerViewHandler
import com.mobiledevpro.chat.main.domain.interactor.ChatPublicInteractor
import com.mobiledevpro.common.ui.base.BaseViewModel
import com.mobiledevpro.rx.RxResult
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

/**
 * ViewModel for public chat
 *
 */

class ChatPublicViewModel(
    private val resourcesProvider: ResourcesProvider,
    private val interactor: ChatPublicInteractor
) : BaseViewModel() {

    private val _listMessages = MutableLiveData<List<RecyclerItem>?>()
    val listMessages: LiveData<List<RecyclerItem>?> = _listMessages

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    val listEventHandler = object : RecyclerViewHandler {
        override fun onClickItem(item: Any) {

            //TODO: handle clicking on items, if needed
        }
    }

    init {
        observeMessagesList()
    }
/*
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStartView() {
        observeMessagesList()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStopView() {
        clearSubscriptions()
    }

 */

    fun isLoadingAnimationVisible(): LiveData<Boolean> {
        val isVisible = MediatorLiveData<Boolean>()

        val b: () -> Boolean = {
            listMessages.value?.isEmpty() ?: true
        }

        isVisible.value = b()

        isVisible.addSource(listMessages) {
            isVisible.value = b()
        }

        return isVisible
    }

    private fun observeMessagesList() {
        interactor.getMessagesList("[some user id]")
            .subscribeBy {
                when (it) {
                    is RxResult.Success ->
                        it.data.toRecyclerView()
                            .let(_listMessages::postValue)

                    is RxResult.Failure ->
                        resourcesProvider.getErrorMessage(it.throwable)
                            .let(_errorMessage::postValue)
                }
            }
            .addTo(subscriptions)
    }
}