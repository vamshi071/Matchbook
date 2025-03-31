package uk.ac.tees.mad.matchbook.data.repository

import kotlinx.coroutines.flow.Flow
import uk.ac.tees.mad.matchbook.data.local.LeaguesDao
import uk.ac.tees.mad.matchbook.data.remote.SportsApiService
import uk.ac.tees.mad.matchbook.model.League
import uk.ac.tees.mad.matchbook.model.LeagueResponse
import uk.ac.tees.mad.matchbook.model.Match
import uk.ac.tees.mad.matchbook.model.MatchResponse
import uk.ac.tees.mad.matchbook.model.Ticket
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val sportsApiService: SportsApiService,
    private val dao:LeaguesDao
):Repository {
    override suspend fun getAllLeagues(): LeagueResponse {
        return sportsApiService.getAllLeagues()
    }

    override suspend fun getMatches(id: String): MatchResponse {
        return sportsApiService.getMatches(id)
    }

    override suspend fun insertLeagues(leagues: List<League>) {
        dao.insertLeagues(leagues)
    }

    override suspend fun insertMatches(matches: List<Match>) {
        dao.insertMatches(matches)
    }

    override suspend fun insertTicket(ticket: Ticket): Long {
        return dao.insertTicket(ticket)
    }

    override fun getLeaguesFromDB(): Flow<List<League>> {
        return dao.getLeaguesFromDB()
    }

    override fun getMatchesFromDB(): Flow<List<Match>> {
        return dao.getMatchesFromDB()
    }

    override fun getMatchByEventId(id: String): Flow<Match?> {
        return dao.getMatchByEventId(id)
    }

    override fun getTicket(id: Long): Flow<Ticket> {
        return dao.getTicket(id)
    }

    override fun getAllTickets(): Flow<List<Ticket>> {
        return dao.getAllTickets()
    }
}