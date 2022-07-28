package com.hamza.loudly.features.git_repo_listing.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.hamza.loudly.features.git_repo_listing.data.remote.GitRepoListingApi
import com.hamza.loudly.features.git_repo_listing.data.remote.GitRepoRemoteDataSourceImpl
import com.hamza.loudly.features.git_repo_listing.data.repository.GitRepoListRepositoryImpl
import com.hamza.loudly.features.git_repo_listing.domain.data_source.GitRepoRemoteDataSource
import com.hamza.loudly.features.git_repo_listing.domain.repository.GitRepoListRepository
import com.hamza.loudly.features.git_repo_listing.domain.use_cases.GitRepoListingUseCases
import com.hamza.loudly.features.git_repo_listing.domain.use_cases.GetListing
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GitRepoListingModule {

    @Provides
    @Singleton
    fun provideRepoListingApi(retrofit: Retrofit): GitRepoListingApi {
        return retrofit.create(GitRepoListingApi::class.java)
    }


    @Provides
    fun provideMoviesRemoteDataSource(
        api: GitRepoListingApi,
    ): GitRepoRemoteDataSource =
        GitRepoRemoteDataSourceImpl(api)

    @Provides
    @Singleton
    fun provideGitRepository(
        api: GitRepoRemoteDataSource
    ): GitRepoListRepository {
        return GitRepoListRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGitRepoListUseCases(repository: GitRepoListRepository): GitRepoListingUseCases {
        return GitRepoListingUseCases(
            getList = GetListing(repository)
        )
    }

}