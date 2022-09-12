package com.mobiledevpro.chat.main.view

import android.util.Log
import androidx.lifecycle.*
import com.mobiledevpro.app.helper.ResourcesProvider
import com.mobiledevpro.chat.core.view.mapper.toRecyclerView
import com.mobiledevpro.chat.core.view.recycler.RecyclerItem
import com.mobiledevpro.chat.core.view.recycler.RecyclerViewHandler
import com.mobiledevpro.chat.main.domain.interactor.ChatPublicInteractor
import com.mobiledevpro.utils.LOG_TAG_DEBUG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch


/**
 * ViewModel for public chat
 *
 */

class ChatPublicViewModel(
    private val resourcesProvider: ResourcesProvider,
    private val interactor: ChatPublicInteractor
) : ViewModel(), DefaultLifecycleObserver {

    private val coroutinesScope = CoroutineScope(Dispatchers.IO)

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

    override fun onDestroy(owner: LifecycleOwner) {
        Log.d(LOG_TAG_DEBUG, "onDestroy: ")
        viewModelScope.cancel()
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
        viewModelScope.launch {
            interactor.getMessagesList()
                .collect {
                    it.onSuccess { messagesList ->
                        _listMessages.value = messagesList.toRecyclerView()
                        Log.d(LOG_TAG_DEBUG, "observeMessagesList: onSuccess: $messagesList ")
                        Log.d(
                            LOG_TAG_DEBUG,
                            "observeMessagesList: onSuccess: Thread ${Thread.currentThread().name} "
                        )
                    }.onFailure {
                        Log.e(
                            LOG_TAG_DEBUG,
                            "observeMessagesList: onFailure: ${it.localizedMessage} "
                        )
                        Log.e(
                            LOG_TAG_DEBUG,
                            "observeMessagesList: onFailure: Thread ${Thread.currentThread().name} "
                        )
                    }
                }

        }


    }
}