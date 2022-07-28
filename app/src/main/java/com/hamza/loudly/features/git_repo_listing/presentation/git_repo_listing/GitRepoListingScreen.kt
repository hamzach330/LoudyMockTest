package com.hamza.loudly.features.git_repo_listing.presentation.git_repo_listing


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hamza.loudly.features.git_repo_listing.domain.model.GitRepoItem
import com.hamza.loudly.features.git_repo_listing.presentation.git_repo_listing.components.*
import com.hamza.loudly.ui.theme.LocalSpacing
import kotlinx.coroutines.flow.Flow

@Composable
fun GitRepoListingScreen(
    navController: NavHostController,
    viewModel: GitRepoListViewModel = hiltViewModel()
) {

    val systemUiController = rememberSystemUiController()
    val systemBarColor = MaterialTheme.colors.primary
    val listData = viewModel.getRepoList

    SideEffect {
        systemUiController.setStatusBarColor(
            color = systemBarColor
        )
    }

    LaunchedEffect(key1 = true) {
        viewModel.search("tetris")
    }
    Scaffold(
        backgroundColor = Color.LightGray,
        contentColor = MaterialTheme.colors.primaryVariant,
        topBar = {
            HomeTopBar()
        },
        content = {
            val searchQuery = viewModel.searchText.collectAsState()

            Column(modifier = Modifier.padding(5.dp)) {

                SearchBar(searchQuery = searchQuery.value, viewModel = viewModel)

                Spacer(modifier = Modifier.size(10.dp))

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .background(MaterialTheme.colors.primary)
                        .padding(0.dp,10.dp)

                ) {
                    GitRepoListViews(flow = listData)
                }


            }
        }
    )
}


@Composable
fun SearchBar(searchQuery: String, viewModel: GitRepoListViewModel) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .background(MaterialTheme.colors.primary)

    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { viewModel.search(query = it) },
            label = { Text("Search") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(LocalSpacing.current.small),
            maxLines = 1
        )
    }

}


@Composable
fun GitRepoListViews(
    flow: Flow<PagingData<GitRepoItem>>
) {
    val lazyPagingItems = flow.collectAsLazyPagingItems()
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 8.dp),
        modifier = Modifier


    ) {
        items(lazyPagingItems) {
            it?.let {
                GitRepoViewItem(item = it)
            }
        }

        lazyPagingItems.apply {
            when {

                loadState.refresh is LoadState.Loading -> {
                    item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                }
                loadState.append is LoadState.Loading -> {
                    item { LoadingItem() }
                }
                loadState.refresh is LoadState.Error -> {
                    val e = lazyPagingItems.loadState.refresh as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage!!,
                            modifier = Modifier.fillMaxSize(),
                            onClickRetry = { retry() }
                        )
                    }
                }
                loadState.append is LoadState.Error -> {
                    val e = lazyPagingItems.loadState.append as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage!!,
                            onClickRetry = { retry() }
                        )
                    }
                }
            }
        }
    }
}
