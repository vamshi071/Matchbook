package uk.ac.tees.mad.matchbook.data.repository

import kotlinx.coroutines.flow.Flow
import uk.ac.tees.mad.matchbook.model.League
import uk.ac.tees.mad.matchbook.model.LeagueResponse

interface Repository {
    suspend fun getAllLeagues(): LeagueResponse
    suspend fun insertLeagues(leagues:List<League>)
    fun getLeaguesFromDB():Flow<List<League>>
}