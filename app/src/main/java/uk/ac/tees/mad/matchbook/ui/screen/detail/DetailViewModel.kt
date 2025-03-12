package uk.ac.tees.mad.matchbook.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uk.ac.tees.mad.matchbook.data.repository.Repository
import uk.ac.tees.mad.matchbook.model.League
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    private val _leagueList = MutableStateFlow(emptyList<League>())
    val leagueList:StateFlow<List<League>> get() = _leagueList

    init {
        viewModelScope.launch {
            repository.getLeaguesFromDB().collect{
                _leagueList.value = it
            }
        }
    }
}