package uk.ac.tees.mad.matchbook.data.repository

import uk.ac.tees.mad.matchbook.model.LeagueResponse

interface Repository {
    suspend fun getAllLeagues(): LeagueResponse
}