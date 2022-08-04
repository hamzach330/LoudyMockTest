package com.hamza.loudly.features.git_repo_listing.domain.data_source

import androidx.paging.PagingData
import com.hamza.loudly.features.git_repo_listing.domain.model.GitRepoItem

import kotlinx.coroutines.flow.Flow

interface GitRepoRemoteDataSource {
    /// this is a comment
    fun getGitRepoList(query: String): Flow<PagingData<GitRepoItem>>
}