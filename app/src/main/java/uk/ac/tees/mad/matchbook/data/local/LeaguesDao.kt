package uk.ac.tees.mad.matchbook.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import uk.ac.tees.mad.matchbook.model.League

@Dao
interface LeaguesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLeagues(leagues:List<League>)

    @Query("SELECT * FROM leagues_table")
    fun getLeaguesFromDB():Flow<List<League>>
}