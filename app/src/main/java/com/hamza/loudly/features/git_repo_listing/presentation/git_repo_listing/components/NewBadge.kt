package com.hamza.loudly.features.git_repo_listing.presentation.git_repo_listing.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hamza.loudly.R
import com.hamza.loudly.ui.theme.NewBadgeBgColor

@Preview
@Composable
fun NewUserBadge() {

    Card(
        shape = RoundedCornerShape(50.dp),
        backgroundColor = NewBadgeBgColor,
    ) {
        Text(
            color = Color.White,
            text = stringResource(R.string.new_str),
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp),
            style = MaterialTheme.typography.button,
            textAlign = TextAlign.Center
        )
    }
}

