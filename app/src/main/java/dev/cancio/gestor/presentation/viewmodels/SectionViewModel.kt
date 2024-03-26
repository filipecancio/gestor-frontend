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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import javax.inject.Inject


@HiltViewModel
class SectionViewModel @Inject constructor(
    private val repository: NewTransactionRepository
) : ViewModel() {
    private val _sectionUiStateFlow: MutableStateFlow<SectionState> =
        MutableStateFlow(SectionState())

    val sectionUiStateFlow: StateFlow<SectionState>
        get() = _sectionUiStateFlow

    fun onStartScreen(month: Int, year: Int) {
        viewModelScope.launch {
            with(repository) {
                combine(
                    getTotalTransactionsValues(month, year),
                    getTransactions(month, year)
                ) { totalValue, currentList ->
                    SectionState(
                        totalValue = totalValue,
                        currentList = currentList
                    )
                }.collectLatest {
                    _sectionUiStateFlow.emit(it)
                }
            }
        }
    }

    fun onCreditCardPressed(filter: FilterType) {
        viewModelScope.launch {
            val newFilterValue = if (filter.isCreditOrNone()) FilterType.DEBT else FilterType.NONE

            val transactionFlow = newFilterValue.toTransactionType()?.let {
                repository.getTransactions(it)
            } ?: repository.getTransactions()

            transactionFlow.collect {
                _sectionUiStateFlow.emit(
                    _sectionUiStateFlow.value.copy(
                        currentList = it,
                        filterValue = newFilterValue
                    )
                )
            }
        }
    }

    fun onDebtCardPressed(filter: FilterType) {
        viewModelScope.launch {
            val newFilterValue = if (filter.isDebtOrNone()) FilterType.CREDIT else FilterType.NONE

            val transactionFlow = newFilterValue.toTransactionType()?.let {
                repository.getTransactions(it)
            } ?: repository.getTransactions()

            transactionFlow.collect {
                _sectionUiStateFlow.emit(
                    _sectionUiStateFlow.value.copy(
                        currentList = it,
                        filterValue = newFilterValue
                    )
                )
            }
        }
    }

    data class SectionState(
        val totalValue: TotalTransactionReport? = null,
        val filterValue: FilterType = FilterType.NONE,
        val currentList: Map<String, List<Transaction>> = emptyMap()
    )
}