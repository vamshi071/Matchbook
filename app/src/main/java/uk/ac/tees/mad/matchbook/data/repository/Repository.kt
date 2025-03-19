package uk.ac.tees.mad.matchbook.data.repository

import kotlinx.coroutines.flow.Flow
import uk.ac.tees.mad.matchbook.model.League
import uk.ac.tees.mad.matchbook.model.LeagueResponse
import uk.ac.tees.mad.matchbook.model.Match
import uk.ac.tees.mad.matchbook.model.MatchResponse

interface Repository {
    suspend fun getAllLeagues(): LeagueResponse
    suspend fun getMatches(id: String): MatchResponse
    suspend fun insertLeagues(leagues:List<League>)
    suspend fun insertMatches(matches: List<Match>)
    fun getLeaguesFromDB():Flow<List<League>>
    fun getMatchesFromDB(): Flow<List<Match>>
    fun getMatchByEventId(id: String): Flow<Match?>
}