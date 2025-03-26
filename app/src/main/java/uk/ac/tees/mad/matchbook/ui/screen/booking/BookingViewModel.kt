package uk.ac.tees.mad.matchbook.ui.screen.booking

import android.app.Application
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import uk.ac.tees.mad.matchbook.data.repository.Repository
import uk.ac.tees.mad.matchbook.model.Match
import uk.ac.tees.mad.matchbook.model.Ticket
import javax.inject.Inject

@HiltViewModel
class BookingViewModel @Inject constructor(
    private val repository: Repository,
    private val application: Application
): ViewModel(){
    private val _match = MutableStateFlow<Match?>(null)
    val match: StateFlow<Match?> get() = _match

    fun fetchMatchData(id: String){
        viewModelScope.launch {
            _match.value = repository.getMatchByEventId(id).first()
        }
    }

    fun addTicket(ticketCount: Int, onResult:(Long)-> Unit){
        viewModelScope.launch {
            val mMatch = _match.value!!
            val id = repository.insertTicket(Ticket(
                idEvent = mMatch.idEvent,
                ticketCount = ticketCount,
                eventName = mMatch.strLeague,
                eventType = mMatch.strEvent,
                location = mMatch.strVenue,
                date = mMatch.dateEvent,
                time = mMatch.strTime,
                qrId = "HJRF2NN",
            ))
            onResult(id)
        }
    }

    fun vibrateOnConfirm(){
        val vibrator = ContextCompat.getSystemService(application.applicationContext,Vibrator::class.java)
        vibrator?.vibrate(VibrationEffect.createOneShot(200,VibrationEffect.DEFAULT_AMPLITUDE))
    }

}