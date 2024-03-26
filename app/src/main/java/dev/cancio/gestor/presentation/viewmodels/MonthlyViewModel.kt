package dev.cancio.gestor.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cancio.gestor.domain.Transaction
import dev.cancio.gestor.repository.NewTransactionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MonthlyViewModel @Inject constructor(
    private val repository: NewTransactionRepository
) : ViewModel() {
    private val _monthlyUiStateFlow: MutableStateFlow<MonthlyState> =
        MutableStateFlow(MonthlyState())

    val monthlyUiStateFlow: StateFlow<MonthlyState>
        get() = _monthlyUiStateFlow

    init {
        onStartScreen()
    }

    private fun onStartScreen() {
        viewModelScope.launch {
            repository.getMonthlyTransactions()
                .collectLatest {
                    _monthlyUiStateFlow.emit(MonthlyState(it))
                }
        }

    }

    data class MonthlyState(
        val currentList: List<Transaction> = listOf()
    )
}