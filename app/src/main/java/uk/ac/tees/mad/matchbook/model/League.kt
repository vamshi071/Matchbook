package uk.ac.tees.mad.matchbook.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "leagues_table")
data class League(
    @PrimaryKey val idLeague: String,
    val strLeague: String,
    val strSport: String,
    val strLeagueAlternate: String?
)
