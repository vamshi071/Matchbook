package uk.ac.tees.mad.matchbook.data.repository

import kotlinx.coroutines.flow.Flow
import uk.ac.tees.mad.matchbook.data.local.LeaguesDao
import uk.ac.tees.mad.matchbook.data.remote.SportsApiService
import uk.ac.tees.mad.matchbook.model.League
import uk.ac.tees.mad.matchbook.model.LeagueResponse
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val sportsApiService: SportsApiService,
    private val dao:LeaguesDao
):Repository {
    override suspend fun getAllLeagues(): LeagueResponse {
        return sportsApiService.getAllLeagues()
    }

    override suspend fun insertLeagues(leagues: List<League>) {
        dao.insertLeagues(leagues)
    }

    override fun getLeaguesFromDB(): Flow<List<League>> {
        return dao.getLeaguesFromDB()
    }
}