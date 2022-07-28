package com.hamza.loudly.features.git_repo_listing.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hamza.loudly.features.git_repo_listing.data.remote.GitRepoListingApi
import com.hamza.loudly.features.git_repo_listing.domain.model.GitRepoItem
import retrofit2.HttpException
import java.io.IOException

class GitRepoListingPagingSource(
    private val query: String,
    private val api: GitRepoListingApi
) : PagingSource<Int, GitRepoItem>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, GitRepoItem> {
        try {

            // Start refresh at page 1 if undefined.
            val nextPageNumber = params.key ?: 1
            val apiResponse = api.getRepositories(
                query = query,
                page = nextPageNumber,
                pageLimit = params.loadSize
            )
            var endOfPaging = false
            if (apiResponse?.items.size < params.loadSize) {
                endOfPaging = true
            }

            return LoadResult.Page(
                data = apiResponse.items.map {
                    val newItem = it.toGitRepoItem()
                    newItem
                },
                prevKey = null, // Only paging forward.
                nextKey = if (endOfPaging) {
                    null
                } else {
                    nextPageNumber + 1
                }
            )
        } catch (e: IOException) {
            // IOException for network failures.
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            // HttpException for any non-2xx HTTP status codes.
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GitRepoItem>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }


}