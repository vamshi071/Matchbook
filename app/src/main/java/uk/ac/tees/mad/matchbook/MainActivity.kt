package uk.ac.tees.mad.matchbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import uk.ac.tees.mad.matchbook.navigation.AppNavigation
import uk.ac.tees.mad.matchbook.ui.theme.MatchBookTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MatchBookTheme(darkTheme = false) {
                AppNavigation()
            }
        }
    }
}