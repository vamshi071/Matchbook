package uk.ac.tees.mad.matchbook.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uk.ac.tees.mad.matchbook.data.repository.Repository
import uk.ac.tees.mad.matchbook.model.League
import uk.ac.tees.mad.matchbook.model.LeagueResponse
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
):ViewModel() {
    private val _response = MutableStateFlow<LeagueResponse?>(null)
    private val _errorMessage = MutableStateFlow<String?>(null)
    private val _leagueList = MutableStateFlow(emptyList<League>())
    val leagueList:StateFlow<List<League>> get() = _leagueList

    init {
        viewModelScope.launch {
            try {
                _response.value = repository.getAllLeagues()
                _leagueList.value = _response.value?.leagues?: emptyList()
            }catch (e : Exception){
                _errorMessage.value = "Error: ${e.message}"
                Log.e("API ERROR", "Error: ${e.message}")
            }
        }
    }
}