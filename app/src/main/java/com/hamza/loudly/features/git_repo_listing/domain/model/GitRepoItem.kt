package com.hamza.loudly.features.git_repo_listing.domain.model

data class GitRepoItem(
    val id: Int,
    val name: String,
    val loginName: String,
    val repoSize: Int,
    val hasWiki: Boolean
)
