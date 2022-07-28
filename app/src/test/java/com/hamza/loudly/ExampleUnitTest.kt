package com.hamza.loudly

import androidx.paging.DifferCallback
import androidx.paging.NullPaddedList
import androidx.paging.PagingData
import androidx.paging.PagingDataDiffer
import app.cash.turbine.test
import com.google.common.truth.Truth
import com.hamza.loudly.data.GitRepoItemFactory
import com.hamza.loudly.data.repository.FakeGitRepoListRepository
import com.hamza.loudly.data.repository.FakeGitRepoListingApi
import com.hamza.loudly.features.git_repo_listing.domain.model.GitRepoItem
import com.hamza.loudly.features.git_repo_listing.domain.use_cases.GetListing
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


    private lateinit var getListing: GetListing
    private lateinit var fakeRepository: FakeGitRepoListRepository
    private lateinit var itemFactory: GitRepoItemFactory
    private lateinit var fakeApi: FakeGitRepoListingApi


    @Before
    fun setUp() {
        fakeApi = FakeGitRepoListingApi()

        fakeRepository = FakeGitRepoListRepository(fakeApi)

        getListing = GetListing(fakeRepository)

        itemFactory = GitRepoItemFactory()

        val itemsToInsert = mutableListOf<GitRepoItem>()
        itemsToInsert.add(itemFactory.ITEM_1)
        itemsToInsert.add(itemFactory.ITEM_2)
        itemsToInsert.add(itemFactory.ITEM_3)

        itemsToInsert.forEach {
            fakeApi.addItem(it)
        }
    }

    @Test
    fun listing_data_is_valid_for_page_size() = runBlocking {

        getListing("").test {
            val item = awaitItem()
            Truth.assertThat(item.collectDataForTest())
                .containsExactlyElementsIn(itemFactory.allItems)
            cancelAndIgnoreRemainingEvents()
        }

    }

    @Test
    fun searched_data_is_valid() = runBlocking {

        getListing("DAVID").test {
            val item = awaitItem()
            Truth.assertThat(item.collectDataForTest()).hasSize(1)
            cancelAndIgnoreRemainingEvents()
        }

    }

}

@OptIn(ExperimentalCoroutinesApi::class)
private suspend fun <T : Any> PagingData<T>.collectDataForTest(): List<T> {
    val dcb = object : DifferCallback {
        override fun onChanged(position: Int, count: Int) {}
        override fun onInserted(position: Int, count: Int) {}
        override fun onRemoved(position: Int, count: Int) {}
    }
    val items = mutableListOf<T>()
    val dif = object : PagingDataDiffer<T>(dcb, UnconfinedTestDispatcher()) {
        override suspend fun presentNewList(
            previousList: NullPaddedList<T>,
            newList: NullPaddedList<T>,
            lastAccessedIndex: Int,
            onListPresentable: () -> Unit
        ): Int? {
            for (idx in 0 until newList.size)
                items.add(newList.getFromStorage(idx))
            onListPresentable()
            return null
        }
    }
    dif.collectFrom(this)
    return items
}