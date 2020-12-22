package com.mobiledevpro.chat.main.view

import androidx.lifecycle.*
import com.mobiledevpro.app.helper.ResourcesProvider
import com.mobiledevpro.chat.core.view.mapper.toRecyclerView
import com.mobiledevpro.chat.core.view.recycler.RecyclerItem
import com.mobiledevpro.chat.core.view.recycler.RecyclerViewHandler
import com.mobiledevpro.chat.main.domain.interactor.ChatPublicInteractor
import com.mobiledevpro.common.ui.base.BaseViewModel
import com.mobiledevpro.domain.core.mapper.RxResult
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

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStartView() {
        observeMessagesList()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStopView() {
        clearSubscriptions()
    }

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
                    is RxResult.Success -> {
                        _listMessages.value = it.data.toRecyclerView()
                    }
                    is RxResult.Failure -> {
                        _errorMessage.value = resourcesProvider.getErrorMessage(it.throwable)
                    }
                }
            }
            .addTo(subscriptions)
    }
}