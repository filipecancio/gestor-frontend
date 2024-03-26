package dev.cancio.gestor.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cancio.gestor.domain.FilterType
import dev.cancio.gestor.domain.TotalTransactionReport
import dev.cancio.gestor.domain.Transaction
import dev.cancio.gestor.repository.NewTransactionRepository
import dev.cancio.gestor.repository.TransactionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: NewTransactionRepository
) : ViewModel() {
    private val _detailUiStateFlow: MutableStateFlow<DetailState> = MutableStateFlow(DetailState())

    val detailUiStateFlow: StateFlow<DetailState>
        get() = _detailUiStateFlow

    fun onStartScreen(transactionId: Int) {
        viewModelScope.launch {
            with(repository) {
                getTransaction(transactionId).collectLatest{ transaction ->
                    getTransactions(transaction.description).collectLatest{ transactionList ->
                        _detailUiStateFlow.emit(
                            DetailState(
                                transaction = transaction,
                                currentList = transactionList
                            )
                        )
                    }
                }
            }
        }
    }

    data class DetailState(
        val transaction: Transaction? = null,
        val currentList: Map<String, List<Transaction>> = emptyMap()
    )
}