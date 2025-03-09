package uk.ac.tees.mad.matchbook.data.remote

import retrofit2.http.GET
import uk.ac.tees.mad.matchbook.model.LeagueResponse

interface SportsApiService {

    @GET("all_leagues.php")
    suspend fun getAllLeagues(): LeagueResponse
}