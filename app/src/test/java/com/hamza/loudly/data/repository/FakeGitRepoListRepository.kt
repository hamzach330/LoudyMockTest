package com.hamza.loudly.data.repository

import androidx.paging.PagingData
import com.hamza.loudly.features.git_repo_listing.data.remote.GitRepoListingApi
import com.hamza.loudly.features.git_repo_listing.domain.model.GitRepoItem
import com.hamza.loudly.features.git_repo_listing.domain.repository.GitRepoListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeGitRepoListRepository(
    private val api: GitRepoListingApi
) : GitRepoListRepository {

    private val items = mutableListOf<GitRepoItem>()


    override fun getListing(query: String): Flow<PagingData<GitRepoItem>> {
        return flow {
            val data = api.getRepositories(query = query, page = 1).items
            emit(PagingData.from(data.map { it.toGitRepoItem() }))
        }
    }
}

