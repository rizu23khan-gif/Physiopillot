package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.data.ImageAsset
import com.example.data.TopicImageGallery
import com.example.data.TopicImageRepository

/**
 * Reusable ImageCard Composable to display a single local asset image with a caption.
 * Gracefully hides itself if the image asset is not physically available in local assets.
 */
@Composable
fun ImageCard(
    image: ImageAsset,
    modifier: Modifier = Modifier,
    onImageClick: ((ImageAsset) -> Unit)? = null,
    onLoadError: (() -> Unit)? = null
) {
    val context = LocalContext.current
    var isAvailable by remember(image.assetPath) {
        mutableStateOf(TopicImageRepository.isAssetAvailable(context, image.assetPath))
    }

    if (!isAvailable) {
        // Hide image container gracefully, returning empty space
        return
    }

    var showZoomDialog by remember { mutableStateOf(false) }

    val coilModel = remember(image.assetPath) {
        ImageRequest.Builder(context)
            .data("file:///android_asset/${image.assetPath}")
            .crossfade(true)
            .build()
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .testTag("image_card_${image.id}"),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clickable {
                        onImageClick?.invoke(image)
                        showZoomDialog = true
                    }
            ) {
                AsyncImage(
                    model = coilModel,
                    contentDescription = image.caption,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                    onError = {
                        // Keep track if image failed to load even after opening
                        isAvailable = false
                        onLoadError?.invoke()
                    }
                )
            }
            if (image.title.isNotEmpty() || image.caption.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    if (image.title.isNotEmpty()) {
                        Text(
                            text = image.title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                    if (image.caption.isNotEmpty()) {
                        Text(
                            text = image.caption,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }

    if (showZoomDialog) {
        ZoomableImageDialog(
            image = image,
            onDismiss = { showZoomDialog = false }
        )
    }
}

/**
 * Reusable ImageGallery Composable to display a horizontal row of image cards.
 * If zero images are available, the container hides entirely.
 */
@Composable
fun ImageGallery(
    gallery: TopicImageGallery,
    modifier: Modifier = Modifier,
    onImageClick: ((ImageAsset) -> Unit)? = null
) {
    if (gallery.images.isEmpty()) {
        return
    }

    val context = LocalContext.current
    val verifiedImages = remember(gallery.images) {
        gallery.images.filter { img ->
            TopicImageRepository.isAssetAvailable(context, img.assetPath)
        }
    }

    if (verifiedImages.isEmpty()) {
        return
    }

    // Keep track of dynamically failed images during decoding/rendering
    val failedImages = remember { mutableStateMapOf<String, Boolean>() }
    val displayableImages = remember(verifiedImages, failedImages.size) {
        verifiedImages.filter { img -> failedImages[img.id] != true }
    }

    if (displayableImages.isEmpty()) {
        return
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .testTag("image_gallery_${gallery.topicId}")
    ) {
        Text(
            text = "Visual learning Reference",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 6.dp)
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(displayableImages, key = { it.id }) { image ->
                ImageCard(
                    image = image,
                    modifier = Modifier.width(280.dp),
                    onImageClick = onImageClick,
                    onLoadError = {
                        failedImages[image.id] = true
                    }
                )
            }
        }
    }
}

/**
 * Interactive full-screen Dialog to let users view illustrations at a larger scale.
 */
@Composable
fun ZoomableImageDialog(
    image: ImageAsset,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = image.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.weight(1f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    TextButton(onClick = onDismiss) {
                        Text("Close")
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(RoundedCornerShape(8.dp))
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data("file:///android_asset/${image.assetPath}")
                            .crossfade(true)
                            .build(),
                        contentDescription = image.caption,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                if (image.caption.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = image.caption,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                    )
                }
            }
        }
    }
}
