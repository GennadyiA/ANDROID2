package com.example.android2.room

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android2.viewmodel.AppState
import com.example.android2.App.Companion.getHistoryDao
import com.example.android2.view.history.LocalRepository
import com.example.android2.view.history.LocalRepositoryImpl

class HistoryViewModel(
    val historyLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val historyRepository: LocalRepository = LocalRepositoryImpl(getHistoryDao())
) : ViewModel() {
    fun getAllHistory() {
        historyLiveData.value = AppState.Loading
        historyLiveData.value = AppState.Success(historyRepository.getAllHistory())
    }
}
