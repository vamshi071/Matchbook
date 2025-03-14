package uk.ac.tees.mad.matchbook.model

data class Match(
    val idEvent: String,
    val strEvent: String,
    val strSport: String,
    val idLeague: String,
    val strLeague: String,
    val strLeagueBadge: String,
    val strHomeTeam: String,
    val strAwayTeam: String,
    val dateEvent: String,
    val strTime: String,
    val strVenue: String,
    val strCountry: String,
    val strStatus: String
)
