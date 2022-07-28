package com.hamza.loudly.features.git_repo_listing.domain.use_cases

import androidx.paging.PagingData
import com.hamza.loudly.features.git_repo_listing.domain.model.GitRepoItem
import com.hamza.loudly.features.git_repo_listing.domain.repository.GitRepoListRepository
import kotlinx.coroutines.flow.Flow

class GetListing(
    private val repository: GitRepoListRepository
) {

    operator fun invoke(query: String): Flow<PagingData<GitRepoItem>> {
        return repository.getListing(query)
    }
}