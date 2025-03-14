package uk.ac.tees.mad.matchbook.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import uk.ac.tees.mad.matchbook.model.LeagueResponse
import uk.ac.tees.mad.matchbook.model.MatchResponse

interface SportsApiService {

    @GET("all_leagues.php")
    suspend fun getAllLeagues(): LeagueResponse

    @GET("eventsnext.php")
    suspend fun getMatches(
        @Query("id") id: String
    ): MatchResponse
}