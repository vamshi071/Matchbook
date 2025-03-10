package uk.ac.tees.mad.matchbook.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uk.ac.tees.mad.matchbook.data.repository.Repository
import uk.ac.tees.mad.matchbook.model.League
import uk.ac.tees.mad.matchbook.model.LeagueResponse
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
):ViewModel() {
    private val _errorMessage = MutableStateFlow<String?>(null)
    private val _leagueList = MutableStateFlow(emptyList<League>())
    val leagueList:StateFlow<List<League>> get() = _leagueList

    init {
        viewModelScope.launch {
            try {
               val response = repository.getAllLeagues().leagues
               repository.insertLeagues(response)
            }catch (e : Exception){
                _errorMessage.value = "Error: ${e.message}"
                Log.e("API ERROR", "Error: ${e.message}")
            }
        }
        viewModelScope.launch {
            repository.getLeaguesFromDB().collect{
                _leagueList.value = it
            }
        }
    }
}