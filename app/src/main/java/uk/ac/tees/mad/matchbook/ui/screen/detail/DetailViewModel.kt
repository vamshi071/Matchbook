package uk.ac.tees.mad.matchbook.ui.screen.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uk.ac.tees.mad.matchbook.data.repository.Repository
import uk.ac.tees.mad.matchbook.model.League
import uk.ac.tees.mad.matchbook.model.Match
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    private val _leagueList = MutableStateFlow(emptyList<League>())
    val leagueList:StateFlow<List<League>> get() = _leagueList

    private val _matchList = MutableStateFlow(emptyList<Match>())
    val matchList: StateFlow<List<Match>> get() = _matchList

    init {
        viewModelScope.launch {
            repository.getLeaguesFromDB().collect{
                _leagueList.value = it
            }
        }
        viewModelScope.launch {
            repository.getMatchesFromDB().collect {
                _matchList.value = it
            }
        }

    }

    fun fetchMatchData(id: String){
        viewModelScope.launch {
            try {
                val matchResponse = repository.getMatches(id)
                repository.insertMatches(matchResponse.events)
            } catch (e: Exception) {
                Log.e("Fetching Match Data", e.message.toString())
            }
        }
    }
}