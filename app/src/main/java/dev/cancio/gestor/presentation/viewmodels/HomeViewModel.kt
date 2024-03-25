package dev.cancio.gestor.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cancio.gestor.domain.TotalTransactionReport
import dev.cancio.gestor.repository.NewTransactionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel  @Inject constructor(
    private val repository: NewTransactionRepository
): ViewModel() {
    val totalValue: MutableStateFlow<TotalTransactionReport?> = MutableStateFlow(null)

    init {
        getTotalTransactionsValues()
    }

    private fun getTotalTransactionsValues() {
        viewModelScope.launch {
            repository.getTotalTransactionsValues()
                .collect{ totalValue.emit(it) }
        }
    }

    data class HomeState(
        val totalValue: TotalTransactionReport
    )
}