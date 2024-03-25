package dev.cancio.gestor.presentation.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cancio.gestor.repository.NewTransactionRepository
import javax.inject.Inject

@HiltViewModel
class MonthlyViewModel  @Inject constructor(
    private val repository: NewTransactionRepository
): ViewModel() {
}