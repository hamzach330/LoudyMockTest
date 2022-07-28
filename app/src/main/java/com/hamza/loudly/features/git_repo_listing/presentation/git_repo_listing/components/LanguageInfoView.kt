package com.hamza.loudly.features.git_repo_listing.presentation.git_repo_listing.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.hamza.loudly.ui.theme.LanguageCodeColor
import com.hamza.loudly.ui.theme.spacing
import com.hamza.loudly.ui.theme.textSize

@Composable
fun LanguageInfoView(modifier: Modifier = Modifier, title: String, languages: String) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.textSize.extraSmall
        )

        Text(
            color = LanguageCodeColor,
            modifier = Modifier.padding(start = MaterialTheme.spacing.extraSmall),
            text = languages,
            fontWeight = FontWeight.SemiBold,
            fontSize = MaterialTheme.textSize.extraExtraSmall
        )
    }
}