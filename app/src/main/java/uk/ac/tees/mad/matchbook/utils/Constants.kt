package uk.ac.tees.mad.matchbook.utils

import androidx.compose.ui.graphics.Color

object Constants {
    fun getColor(idx:Int):Color{
        return when(idx){
            0 -> Color(0xFF83c5be)
            1 -> Color(0xFFffddd2)
            2 -> Color(0xFFe3d5ca)
            3-> Color(0xFFbcb8b1)
            4-> Color(0xFFe1e5ee)
            5-> Color(0xFFddbdd5)
            else -> Color(0xFF4cc9f0)
        }
    }
}