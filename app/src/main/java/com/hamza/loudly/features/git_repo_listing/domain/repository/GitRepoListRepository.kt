package com.hamza.loudly.features.git_repo_listing.domain.repository

import androidx.paging.PagingData
import com.hamza.loudly.features.git_repo_listing.domain.model.GitRepoItem
import kotlinx.coroutines.flow.Flow

interface GitRepoListRepository {

    fun getListing(query: String): Flow<PagingData<GitRepoItem>>
}