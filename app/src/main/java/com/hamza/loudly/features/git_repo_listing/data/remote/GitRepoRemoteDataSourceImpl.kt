package com.hamza.loudly.features.git_repo_listing.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hamza.loudly.features.git_repo_listing.data.remote.paging.GitRepoListingPagingSource
import com.hamza.loudly.features.git_repo_listing.domain.data_source.GitRepoRemoteDataSource
import com.hamza.loudly.features.git_repo_listing.domain.model.GitRepoItem

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

const val API_PAGE_SIZE = 10

class GitRepoRemoteDataSourceImpl @Inject constructor(
    private val api: GitRepoListingApi
) : GitRepoRemoteDataSource {

    override fun getGitRepoList(query: String): Flow<PagingData<GitRepoItem>> {
        return Pager(
            config = PagingConfig(pageSize = API_PAGE_SIZE)
        ) {
            GitRepoListingPagingSource(query = query, api = api)

        }.flow
    }

}