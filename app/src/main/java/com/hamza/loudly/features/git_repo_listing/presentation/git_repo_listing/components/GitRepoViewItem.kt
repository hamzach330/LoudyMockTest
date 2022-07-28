package com.hamza.loudly.features.git_repo_listing.presentation.git_repo_listing.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hamza.loudly.features.git_repo_listing.domain.model.GitRepoItem

/**
 * this is called state hoisting.
 * Instead of hard coding the viewmodel in this view,
 * we pass the events to the parent class, this allows us to reuse this view with other screens.
 */
@Composable
fun GitRepoViewItem(
    item: GitRepoItem
) {

    Card(
        elevation = 10.dp,
        modifier = Modifier
            // .padding(10.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .background(color = if (item.hasWiki == true) Color.Green else Color.LightGray)
                .padding(all = 5.dp)
        ) {

            Row() {
                Text(
                    text = "Name: ",
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.h6
                )
                item.name?.let {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = it,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium,
                        style = MaterialTheme.typography.h6
                    )
                }


            }



            Row() {

                Text(

                    text = "Login Name: ",
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.h6
                )

                item.loginName?.let {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = it,
                        color = Color.Black,
                        fontWeight = FontWeight.Light,
                        style = MaterialTheme.typography.h6
                    )
                }
            }

            Row() {

                Text(
                    text = "Repo Size: ",
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.h6
                )


                 item.repoSize?.let {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = it.toString(),
                        color = Color.Black,
                        fontWeight = FontWeight.Light,
                        style = MaterialTheme.typography.h6
                    )
                }

            }
        }

    }
}


