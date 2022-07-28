package com.hamza.loudly.features.git_repo_listing.data.remote

import com.hamza.loudly.features.git_repo_listing.data.remote.dto.ResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GitRepoListingApi {

    @GET("search/repositories")
    suspend fun getRepositories(@Query("q") query: String, @Query("page") page: Int, @Query("per_page") pageLimit : Int = API_PAGE_SIZE): ResponseDto
}