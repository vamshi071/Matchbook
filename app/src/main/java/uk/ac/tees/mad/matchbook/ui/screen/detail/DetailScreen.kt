package uk.ac.tees.mad.matchbook.ui.screen.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import uk.ac.tees.mad.matchbook.ui.screen.detail.components.MatchItem
import uk.ac.tees.mad.matchbook.ui.screen.home.components.LeagueItem
import uk.ac.tees.mad.matchbook.utils.Constants.getColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    id: String,
    viewModel: DetailViewModel = hiltViewModel(),
) {
    val leagues by viewModel.leagueList.collectAsState()
    val matches by viewModel.matchList.collectAsState()

    LaunchedEffect(id) {
        viewModel.fetchMatchData(id)
    }
    Scaffold(
        topBar = {
            Text(
                "Matches",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(top = 30.dp, bottom = 12.dp,start = 16.dp, end = 16.dp)
            )
        }
    ) { paddingValues ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(paddingValues)){
            Column {
                HorizontalMultiBrowseCarousel(
                    state = rememberCarouselState { matches.size },
                    preferredItemWidth = 290.dp,
                    itemSpacing = 12.dp,
                    contentPadding = PaddingValues(horizontal = 8.dp)
                ) { idx->
                    val match = matches[idx]
                    MatchItem(match)
                }

                Text("Explore other leagues", modifier = Modifier.padding(16.dp))
                LazyHorizontalStaggeredGrid(
                    rows = StaggeredGridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.height(200.dp)

                ) {
                    itemsIndexed(leagues){ idx,league->
                        LeagueItem(league, getColor(idx%6)){
                            viewModel.fetchMatchData(league.idLeague)
                        }
                    }
                }
            }
        }
    }
}