package uk.ac.tees.mad.matchbook.ui.screen.booking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import uk.ac.tees.mad.matchbook.data.repository.Repository
import uk.ac.tees.mad.matchbook.model.Match
import javax.inject.Inject

@HiltViewModel
class BookingViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    private val _match = MutableStateFlow<Match?>(null)
    val match: StateFlow<Match?> get() = _match

    fun fetchMatchData(id: String){
        viewModelScope.launch {
            _match.value = repository.getMatchByEventId(id).first()
        }
    }
}