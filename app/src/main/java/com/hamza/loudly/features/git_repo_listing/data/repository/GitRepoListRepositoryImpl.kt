package com.hamza.loudly.features.git_repo_listing.data.repository

import com.hamza.loudly.features.git_repo_listing.domain.data_source.GitRepoRemoteDataSource
import com.hamza.loudly.features.git_repo_listing.domain.repository.GitRepoListRepository
import javax.inject.Inject

class GitRepoListRepositoryImpl @Inject constructor(
    private val api: GitRepoRemoteDataSource,
) : GitRepoListRepository {

    override fun getListing(query: String) = api.getGitRepoList(query = query)
}