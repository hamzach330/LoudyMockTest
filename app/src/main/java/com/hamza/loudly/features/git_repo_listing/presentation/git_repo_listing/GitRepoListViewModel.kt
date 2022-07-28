package com.hamza.loudly.features.git_repo_listing.presentation.git_repo_listing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.hamza.loudly.features.git_repo_listing.domain.use_cases.GitRepoListingUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class GitRepoListViewModel @Inject constructor(
    private val useCases: GitRepoListingUseCases
) : ViewModel() {

    private val searchQuery = MutableStateFlow("")
    val searchText = searchQuery.asStateFlow()
    val getRepoList = searchQuery.debounce(300)
        .distinctUntilChanged()
        .flatMapLatest {
            useCases.getList.invoke(it).cachedIn(viewModelScope)
        }

    fun search(query: String) {
        searchQuery.value = query
    }
}