package com.example.gamehok.presentation.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gamehok.data.model.GameData
import com.example.gamehok.data.model.TournamentData
import com.example.gamehok.data.util.ApiResult
import com.example.gamehok.presentation.theme.BackGroundColor
import com.example.gamehok.presentation.ui.componenets.CustomTopAppBar
import com.example.gamehok.presentation.ui.componenets.HeadingText
import com.example.gamehok.presentation.ui.componenets.PremiumCard
import com.example.gamehok.presentation.ui.componenets.TournamentPlaceHolder
import kotlinx.coroutines.launch



@Composable
fun HomeScreen(
    gameList: ApiResult<List<GameData>>,
    tournamentList: ApiResult<List<TournamentData>>,
    homeScreenEvents: (HomeScreenEvents)->Unit) {
    val pagerState = rememberPagerState(pageCount = { 5 })

    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        topBar = {
           CustomTopAppBar()
        }
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

            item { HeadingText(text="Play Tournament by Games") }

            item{
                when(gameList){
                    is ApiResult.Error -> {
                        Column(
                            modifier = Modifier
                                .background(Color.Black)
                            ,
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(paddingValues), contentAlignment = Alignment.Center
                            ) {
                                Text(text = "Error: ${gameList.message}")
                            }

                        }
                    }
                    ApiResult.Loading -> {
                        CircularProgressIndicator(color = Color.White)
                    }
                    is ApiResult.Success -> {
                        LazyRow(
                            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                            verticalAlignment = Alignment.CenterVertically ,
                            horizontalArrangement = Arrangement.Center
                        ){
                            gameList.data?.run {
                                itemsIndexed(this) { index, item ->
                                    TournamentPlaceHolder( data = item)
                                       // homeScreenEvents.invoke(HomeScreenEvents.OnNavigate(item.link))
                                }
                            }
                        }
                    }
                }
            }

        }
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