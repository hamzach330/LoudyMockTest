package com.hamza.loudly.features.git_repo_listing.data.remote.dto

data class ResponseDto(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<Item>
)