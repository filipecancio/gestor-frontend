package dev.cancio.gestor.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cancio.gestor.domain.FilterType
import dev.cancio.gestor.domain.TotalTransactionReport
import dev.cancio.gestor.domain.Transaction
import dev.cancio.gestor.repository.NewTransactionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import javax.inject.Inject
import kotlin.math.absoluteValue

@HiltViewModel
class HomeViewModel  @Inject constructor(
    private val repository: NewTransactionRepository
): ViewModel() {
    private val _homeUiStateFlow: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())

    val homeUiStateFlow : StateFlow<HomeState>
        get() = _homeUiStateFlow

    init {
        getTotalTransactionsValues()
    }

    private fun getTotalTransactionsValues() {
        viewModelScope.launch {
            repository.getTotalTransactionsValues()
                .collect{ _homeUiStateFlow.emit(_homeUiStateFlow.value.copy(
                    totalValue = it
                )) }
        }
    }

    private fun getTransactions() {
        viewModelScope.launch {
            repository.getTransactions()
                .collect{
                    _homeUiStateFlow.emit(_homeUiStateFlow.value.copy(
                        currentList = it
                    )) }
        }
    }

    fun onCreditCardPressed(filter: FilterType) {
        viewModelScope.launch {
            val newFilterValue = if(filter.isCreditOrNone()) FilterType.DEBT else FilterType.NONE

            val transactionFlow = newFilterValue.toTransactionType()?.let {
                repository.getTransactions(it) } ?: repository.getTransactions()

            transactionFlow.collect{
                _homeUiStateFlow.emit(_homeUiStateFlow.value.copy(
                    currentList = it,
                    filterValue = newFilterValue
                ))
            }
        }
    }

    fun onDebtCardPressed(filter: FilterType) {
        viewModelScope.launch {
            val newFilterValue = if(filter.isDebtOrNone()) FilterType.CREDIT else FilterType.NONE

            val transactionFlow = newFilterValue.toTransactionType()?.let {
                repository.getTransactions(it) } ?: repository.getTransactions()

            transactionFlow.collect{
                _homeUiStateFlow.emit(_homeUiStateFlow.value.copy(
                    currentList = it,
                    filterValue = newFilterValue
                ))
            }
        }
    }

    data class HomeState(
        val totalValue: TotalTransactionReport? = null,
        val filterValue: FilterType = FilterType.NONE,
        val currentList: Map<String, List<Transaction>> = emptyMap()
    )
}