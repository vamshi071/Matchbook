package uk.ac.tees.mad.matchbook.ui.screen.confirmation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uk.ac.tees.mad.matchbook.data.repository.Repository
import uk.ac.tees.mad.matchbook.model.Ticket
import javax.inject.Inject

@HiltViewModel
class ConfirmViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    private val _ticket = MutableStateFlow<Ticket?>(null)
    val ticket: StateFlow<Ticket?> get() = _ticket

    fun loadTicket(id: Long){
        Log.d("Confirm Debug", "Id in viewModel: $id")
        viewModelScope.launch {
            repository.getTicket(id).collect {
                _ticket.value = it
            }
        }

    }
}