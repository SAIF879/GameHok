package com.example.gamehok.presentation.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.gamehok.data.model.GameData
import com.example.gamehok.data.model.TournamentData
import com.example.gamehok.data.model.peopleList
import com.example.gamehok.data.util.ApiResult
import com.example.gamehok.presentation.theme.BackGroundColor
import com.example.gamehok.presentation.ui.componenets.CustomTopAppBar
import com.example.gamehok.presentation.ui.componenets.GameCard
import com.example.gamehok.presentation.ui.componenets.HeadingText
import com.example.gamehok.presentation.ui.componenets.PeopleCard
import com.example.gamehok.presentation.ui.componenets.PremiumCard
import com.example.gamehok.presentation.ui.componenets.TournamentCard
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    gameList: ApiResult<List<GameData>>,
    tournamentList: ApiResult<List<TournamentData>>,
    homeScreenEvents: (HomeScreenEvents) -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { 5 })

    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        topBar = { CustomTopAppBar() }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .background(color = BackGroundColor)
                .padding(paddingValues)
                .padding(horizontal = 10.dp)
                .fillMaxSize()
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                PremiumPagerSection(pagerState = pagerState)
                Spacer(modifier = Modifier.height(8.dp))
                PagerIndicator(pagerState = pagerState)
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Play Tournament by Games section
            item {
                HeadingText(text = "Play Tournament by Games")
                Spacer(modifier = Modifier.height(8.dp))
                ApiResultSection(
                    result = gameList,
                    onSuccess = { games ->
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(games, key = { it.id ?: -1 }) { item ->
                                GameCard(data = item)
                            }
                        }
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            item { HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp)) }

            // Compete in Battles section
            item {
                HeadingText(text = "Compete in Battles")
                Spacer(modifier = Modifier.height(8.dp))
                ApiResultSection(
                    result = tournamentList,
                    onSuccess = { tournaments ->
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(tournaments, key = { it.id ?: -1 }) { item ->
                                TournamentCard(data = item)
                            }
                        }
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            // People To Follow section
            item {
                HeadingText(text = "People To Follow")
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(peopleList, key = { it.name }) { person ->
                PeopleCard(person)
            }

        }
    }
}

@Composable
fun <T> ApiResultSection(
    result: ApiResult<T>,
    onSuccess: @Composable (T) -> Unit
) {
    when (result) {
        is ApiResult.Error -> Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Error: ${result.message}", color = Color.Red)
        }

        ApiResult.Loading -> Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color.White)
        }

        is ApiResult.Success -> result.data?.let { onSuccess(it) }
    }
}




@Composable
fun PremiumPagerSection(pagerState: PagerState) {
    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp) // Adjust height as needed
    ) { page ->
        PremiumCard(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            onUpgradeClick = { /* Handle upgrade */ },
            onViewFeaturesClick = { /* Handle view features */ }
        )
    }
}


@Composable
fun PagerIndicator(pagerState: PagerState) {
    val coroutineScope = rememberCoroutineScope()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerState.pageCount) { index ->
            val color = if (pagerState.currentPage == index) {
                MaterialTheme.colorScheme.primary
            } else {
                Color.Gray
            }

            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(8.dp)
                    .clickable {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
            )
        }
    }
}