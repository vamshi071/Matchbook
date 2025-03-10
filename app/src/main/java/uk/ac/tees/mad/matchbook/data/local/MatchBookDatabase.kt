package uk.ac.tees.mad.matchbook.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uk.ac.tees.mad.matchbook.model.League

@Database(entities = [League::class], version = 1, exportSchema = false)
abstract class MatchBookDatabase:RoomDatabase() {
    abstract fun leaguesDao():LeaguesDao

    companion object{
        @Volatile
        private var INSTANCE:MatchBookDatabase? = null

        fun getDatabase(context: Context):MatchBookDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MatchBookDatabase::class.java,
                    "match_book_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}