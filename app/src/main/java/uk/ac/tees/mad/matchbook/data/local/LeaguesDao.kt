package uk.ac.tees.mad.matchbook.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import uk.ac.tees.mad.matchbook.model.League
import uk.ac.tees.mad.matchbook.model.Match
import uk.ac.tees.mad.matchbook.model.Ticket

@Dao
interface LeaguesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLeagues(leagues:List<League>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMatches(matches: List<Match>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTicket(ticket: Ticket): Long

    @Query("SELECT * FROM leagues_table")
    fun getLeaguesFromDB():Flow<List<League>>

    @Query("SELECT * FROM match_table")
    fun getMatchesFromDB(): Flow<List<Match>>

    @Query("SELECT * FROM match_table WHERE idEvent= :id LIMIT 1")
    fun getMatchByEventId(id: String): Flow<Match?>

    @Query("SELECT * FROM ticket_table WHERE id = :id LIMIT 1")
    fun getTicket(id: Long): Flow<Ticket>
}