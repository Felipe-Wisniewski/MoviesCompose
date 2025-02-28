package com.wisnitech.moviescompose.ui.details

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.wisnitech.repository.model.MovieDetails
import com.wisnitech.repository.model.Person
import com.wisnitech.moviescompose.ui.R
import com.wisnitech.moviescompose.ui.components.LoadingView

@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    onNavigateToPlayer: (movieKey: String) -> Unit
) {
    val uiState by viewModel.detailsUiState.collectAsStateWithLifecycle()
    val isWatchlist by viewModel.isWatchlist.collectAsStateWithLifecycle()
    val feedbackActions by viewModel.feedbackActions.collectAsStateWithLifecycle()

    when (uiState) {
        MovieDetailsUiState.Loading -> {
            LoadingView()
        }

        is MovieDetailsUiState.Success -> {
            val movie = (uiState as MovieDetailsUiState.Success).movieDetails

            DetailsScreen(
                movie,
                isWatchlist,
                onTrailerClick = { onNavigateToPlayer(it) },
                onWatchlistClick = { viewModel.saveOrRemoveToWatchlist(movie.isWatchlist) },
                onLikeClick = viewModel::setLikeMovie,
                onUnlikeClick = viewModel::setUnlikeMovie,
                onShareClick = { }
            )
        }
    }

    val toastMessage = when (feedbackActions) {
        DetailsActions.ADD_WATCHLIST -> "Added to Watchlist!"
        DetailsActions.REMOVE_WATCHLIST -> "Removed to Watchlist!"
        DetailsActions.ERROR_WATCHLIST -> "Error to Add/Remove Watchlist!"
        null -> null
    }

    toastMessage?.let {
        Toast.makeText(LocalContext.current, it, Toast.LENGTH_SHORT).show()
    }

}

@Composable
fun DetailsScreen(
    movieDetails: MovieDetails,
    isWatchlist: Boolean,
    onTrailerClick: (movieKey: String) -> Unit,
    onWatchlistClick: () -> Unit,
    onLikeClick: () -> Unit,
    onUnlikeClick: () -> Unit,
    onShareClick: () -> Unit
) {
    var widthSize by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .onGloballyPositioned { coordinates ->
                widthSize = with(density) {
                    coordinates.size.width.toDp()
                }
            }
            .verticalScroll(rememberScrollState())
    ) {
        MovieImage(movieDetails.backdropUrl)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp)
        ) {
            movieDetails.tagline?.let {
                if (it.isNotBlank()) TaglineText(it)
            }

            TitleText(movieDetails.title ?: "")

            IncludedStreams()

            Spacer(modifier = Modifier.height(16.dp))

            OptionsButtons(
                movieDetails = movieDetails,
                isWatchlist = isWatchlist,
                onTrailerClick = { onTrailerClick(it) },
                onWatchlistClick = { onWatchlistClick() },
                onLikeClick = { onLikeClick() },
                onUnlikeClick = { onUnlikeClick() },
                onShareClick = { onShareClick() }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OverviewText(text = movieDetails.overview ?: "")

            Spacer(modifier = Modifier.height(8.dp))

            movieDetails.genres?.let { GenresList(it) }

            Spacer(modifier = Modifier.height(12.dp))

            // IMDb 9.2
            Text(text = "IMDb ${movieDetails.voteAverage}", color = Color.Gray)

            Spacer(modifier = Modifier.height(8.dp))

            // 1972 169 min
            Text(
                text = "${movieDetails.releaseDate}  ${movieDetails.runtime}min",
                color = Color.Gray
            )

            // Languages
            // Audio (2), Subtitles (1)

            // Related / More details

            Spacer(modifier = Modifier.height(16.dp))

            // Cast & Crew
            Text(text = "Cast & Crew")

            Spacer(modifier = Modifier.height(2.dp))

            Text(text = "Details from TMDb", fontSize = 12.sp, color = Color.Gray)

            Spacer(modifier = Modifier.height(8.dp))

            if (movieDetails.cast?.isNotEmpty() == true) {
                CastRows(widthSize, movieDetails.cast!!)
            } else {
                Text(text = "We don´t have any cast information.", fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Spacer(
                modifier = Modifier
                    .background(Color.Gray)
                    .fillMaxWidth()
                    .height(0.5.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            movieDetails.director?.let {
                DirectorField(widthSize, it)
            }
        }
    }
}

@Composable
fun MovieImage(imageUrl: String?) {
    AsyncImage(
        modifier = Modifier.fillMaxWidth(),
        model = imageUrl,
        contentDescription = "backdrop image",
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
fun OptionsButtons(
    movieDetails: MovieDetails,
    isWatchlist: Boolean,
    onTrailerClick: (videoKey: String) -> Unit,
    onWatchlistClick: () -> Unit,
    onLikeClick: () -> Unit,
    onUnlikeClick: () -> Unit,
    onShareClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        movieDetails.trailer?.trailerKey?.let {
            OptionButton(ImageVector.vectorResource(R.drawable.ic_movie), "Trailer") {
                onTrailerClick(it)
            }
        }

        val watchlistIcon = if (isWatchlist) Icons.Outlined.Check else Icons.Outlined.Add
        OptionButton(watchlistIcon, "Watchlist") {
            onWatchlistClick()
        }

        OptionButton(Icons.Outlined.ThumbUp, "Like") {
            onLikeClick()
        }

        OptionButton(ImageVector.vectorResource(R.drawable.ic_thumb_down), "Not for me") {
            onUnlikeClick()
        }

        OptionButton(Icons.Outlined.Share, "Share") {
            onShareClick()
        }
    }
}

@Composable
fun OptionButton(
    icon: ImageVector,
    description: String,
    onClickOption: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(onClick = { onClickOption() }) {
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

@Composable
fun GenresList(genres: List<String>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        genres.forEachIndexed { index, genre ->
            Text(text = genre)

            Spacer(modifier = Modifier.size(8.dp))

            if (index != genres.lastIndex) {
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .size(4.dp)
                        .padding(4.dp)
                        .clip(RoundedCornerShape(2.dp))
                )

                Spacer(modifier = Modifier.size(8.dp))
            }
        }
    }
}

@Composable
fun CastRows(widthSize: Dp, cast: List<Person>) {
    val firstRowCast = mutableListOf<Person>()
    val secondRowCast = mutableListOf<Person>()

    cast.forEachIndexed { index, person ->
        when (index) {
            0, 1, 2 -> firstRowCast.add(person)
            3, 4, 5 -> secondRowCast.add(person)
        }
    }

    CastRow(widthSize, firstRowCast)

    Spacer(modifier = Modifier.height(16.dp))

    CastRow(widthSize, secondRowCast)
}

@Composable
fun CastRow(widthSize: Dp, cast: List<Person>) {
    val imageWidth = (widthSize - 48.dp) / 3

    if (cast.isNotEmpty()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            cast.forEach {
                ItemPersonImageName(imageWidth, it.profileUrl, it.name)
            }
        }
    }
}

@Composable
fun DirectorField(widthSize: Dp, director: Person) {
    val imageWidth = (widthSize - 48.dp) / 3

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
    ) {
        ItemPersonImageName(imageWidth, director.profileUrl, director.name)

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(text = "Director")

            Spacer(modifier = Modifier.height(4.dp))

            Text(text = "Known for: Bla bla bla", color = Color.Gray)
        }
    }
}

@Composable
fun ItemPersonImageName(imageWidth: Dp, imageUrl: String?, name: String) {
    Box(modifier = Modifier.width(imageWidth)) {
        AsyncImage(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .clickable { },
            model = imageUrl,
            contentDescription = "person image",
        )

        Text(
            text = name,
            modifier = Modifier.align(Alignment.BottomCenter),
            fontSize = 11.sp
        )
    }
}
