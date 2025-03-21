package uk.ac.tees.mad.matchbook.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ticket_table")
data class Ticket(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val idEvent: String,
    val ticketCount: Int,
    val timeStamp: Long = System.currentTimeMillis()
)
