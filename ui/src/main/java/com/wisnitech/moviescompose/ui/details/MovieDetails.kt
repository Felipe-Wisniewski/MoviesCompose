package com.wisnitech.moviescompose.ui.details

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.wisnitech.data.models.MovieDetails
import com.wisnitech.moviescompose.ui.R
import com.wisnitech.moviescompose.ui.common.LoadingView

@Composable
fun MovieDetailsScreen(viewModel: MovieDetailsViewModel = hiltViewModel()) {

    val uiState by viewModel.detailsUiState.collectAsStateWithLifecycle()

    when (uiState) {
        MovieDetailsUiState.Loading -> {
            LoadingView()
        }

        is MovieDetailsUiState.Success -> {
            DetailsScreen((uiState as MovieDetailsUiState.Success).movieDetails)
        }
    }
}

@Composable
fun DetailsScreen(movieDetails: MovieDetails) {

    Column(modifier = Modifier.fillMaxSize()) {
        MovieImage(movieDetails.title ?: "", movieDetails.getBackdropUrl())

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp)
        ) {
            TaglineText(movieDetails.tagline ?: "")

            TitleText(movieDetails.title ?: "")

            IncludedStreams()

            Spacer(modifier = Modifier.height(16.dp))

            OptionsButtons()

            Spacer(modifier = Modifier.height(16.dp))

            OverviewText(text = movieDetails.overview ?: "")

            // genres

            // IMDb 9.2

            // 1972 169 min

            // Languages
            // Audio (2), Subtitles (1)
        }
    }

}

@Composable
fun MovieImage(title: String, imageUrl: String?) {
    AsyncImage(
        modifier = Modifier.fillMaxWidth(),
        model = imageUrl,
        contentDescription = "poster do filme $title",
    )
}

@Preview
@Composable
fun TaglineText(tagline: String = "Whoever saves one life, saves the world entire.") {
    Text(
        text = tagline,
        fontFamily = FontFamily.Default,
        fontSize = 18.sp
    )
}

@Preview
@Composable
fun TitleText(title: String = "Schindler's List") {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    )
}

@Preview
@Composable
fun IncludedStreams() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_check_circle),
            "",
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = "Included with prime",
            modifier = Modifier.padding(start = 4.dp),
            fontSize = 11.sp
        )
    }
}

@Composable
fun OptionsButtons() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OptionButton(ImageVector.vectorResource(R.drawable.ic_movie), "Trailer") {

        }

        OptionButton(Icons.Outlined.Add, "Watchlist") {

        }

        OptionButton(Icons.Outlined.ThumbUp, "Like") {

        }

        OptionButton(ImageVector.vectorResource(R.drawable.ic_thumb_down), "Not for me") {

        }

        OptionButton(Icons.Outlined.Share, "Share") {

        }
    }
}

@Composable
fun OptionButton(
    icon: ImageVector,
    description: String,
    onClickOption: (option: String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(onClick = { onClickOption.invoke(description) }) {
            Icon(imageVector = icon, contentDescription = description)
        }

        Text(text = description, fontSize = 11.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun OverviewText(text: String) {

    var lines by remember { mutableIntStateOf(3) }

    Text(
        text = text,
        modifier = Modifier.clickable { lines = Int.MAX_VALUE },
        overflow = TextOverflow.Ellipsis,
        maxLines = lines
    )
}