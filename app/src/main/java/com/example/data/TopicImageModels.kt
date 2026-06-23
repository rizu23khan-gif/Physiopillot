package com.example.data

import kotlinx.serialization.Serializable

@Serializable
data class ImageAsset(
    val id: String,
    val title: String,
    val assetPath: String,
    val caption: String
)

@Serializable
data class TopicImageGallery(
    val topicId: String,
    val images: List<ImageAsset>
)
