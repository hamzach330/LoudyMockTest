package com.hamza.loudly.data.repository

import com.hamza.loudly.features.git_repo_listing.data.remote.GitRepoListingApi
import com.hamza.loudly.features.git_repo_listing.data.remote.dto.Item
import com.hamza.loudly.features.git_repo_listing.data.remote.dto.ResponseDto
import com.hamza.loudly.features.git_repo_listing.domain.model.GitRepoItem

class FakeGitRepoListingApi : GitRepoListingApi {

    private val model = mutableListOf<GitRepoItem>()

    fun addItem(item: GitRepoItem) {
        model.add(item)
    }

    override suspend fun getRepositories(query: String, page: Int, pageLimit: Int): ResponseDto {
        val data = when (query.isBlank()) {
            true -> model
            false -> model.filter { x ->
                x.name.contains(query) ||
                        x.loginName.contains(query)
            }
        }

        val transformedList = data.map {
            Item(
                name = it.name,
                size = it.repoSize,
                full_name = it.loginName
            )
        }

        return ResponseDto(
            items = transformedList,
            incomplete_results = true,
            total_count = transformedList.size
        )
    }

}