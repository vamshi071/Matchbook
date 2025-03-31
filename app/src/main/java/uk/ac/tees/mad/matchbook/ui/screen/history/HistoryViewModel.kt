package uk.ac.tees.mad.matchbook.ui.screen.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import uk.ac.tees.mad.matchbook.data.repository.Repository
import uk.ac.tees.mad.matchbook.model.Ticket
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    private val _ticketList = MutableStateFlow(emptyList<Ticket>())
    val ticketList: StateFlow<List<Ticket>> get() = _ticketList

    init {
        viewModelScope.launch {
            _ticketList.value = repository.getAllTickets().first()
        }
    }
}