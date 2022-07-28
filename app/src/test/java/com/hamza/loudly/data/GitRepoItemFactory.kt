package com.hamza.loudly.data

import com.hamza.loudly.features.git_repo_listing.data.remote.dto.Item

class GitRepoItemFactory {


    val ITEM_1 = Item(
        name = "DAVID",
        size = 10,
        full_name = "Test Name"
    ).toGitRepoItem()

    val ITEM_2 = Item(
        name = "Adam",
        size = 290,
        full_name = "Test Name 2"
    ).toGitRepoItem()

    val ITEM_3 = Item(
        name = "Jill",
        size = 0,
        full_name = "Test Name"
    ).toGitRepoItem()


    val allItems = listOf(ITEM_1, ITEM_2, ITEM_3)


}