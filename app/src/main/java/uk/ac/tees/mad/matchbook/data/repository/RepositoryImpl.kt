package uk.ac.tees.mad.matchbook.data.repository

import uk.ac.tees.mad.matchbook.data.remote.SportsApiService
import uk.ac.tees.mad.matchbook.model.LeagueResponse
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val sportsApiService: SportsApiService
):Repository {
    override suspend fun getAllLeagues(): LeagueResponse {
        return sportsApiService.getAllLeagues()
    }
}