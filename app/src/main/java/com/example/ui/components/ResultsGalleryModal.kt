package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.example.data.model.BeforeAfterResult
import com.example.ui.theme.*

@Composable
fun ResultsGalleryModal(
    results: List<BeforeAfterResult>,
    initialResult: BeforeAfterResult?,
    onDismiss: () -> Unit,
    disclaimerText: String
) {
    val published = results.filter { it.isPublished && it.hasOwnerPermission }
    var selectedCategory by remember { mutableStateOf("All") }

    val categories = remember(published) {
        listOf("All") + published.map { it.category }.filter { it.isNotBlank() }.distinct()
    }

    val filteredList = remember(published, selectedCategory) {
        if (selectedCategory == "All") published else published.filter { it.category == selectedCategory }
    }

    var currentIndex by remember(initialResult, filteredList) {
        mutableStateOf(
            if (initialResult != null) {
                val idx = filteredList.indexOfFirst { it.id == initialResult.id }
                if (idx >= 0) idx else 0
            } else 0
        )
    }

    val currentItem = filteredList.getOrNull(currentIndex)

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Slate950
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding()
                    .padding(16.dp)
            ) {
                // Top Action Bar with Close
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "RESULTS GALLERY",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.2.sp,
                            color = GoldAccent
                        )
                        Text(
                            text = "Verified Transformations",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Slate50
                        )
                    }

                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Slate800)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close gallery",
                            tint = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Category Chips
                if (categories.size > 1) {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(categories) { cat ->
                            FilterChip(
                                selected = selectedCategory == cat,
                                onClick = {
                                    selectedCategory = cat
                                    currentIndex = 0
                                },
                                label = { Text(cat, fontSize = 12.sp) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = CyanPrimary,
                                    selectedLabelColor = Color.White
                                )
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }

                if (currentItem == null) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No verified results in this category yet.",
                            color = Slate400,
                            fontSize = 14.sp
                        )
                    }
                } else {
                    // Current Item Viewer
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = currentItem.title,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Slate50
                            )
                            if (currentItem.duration.isNotBlank()) {
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = Slate800
                                ) {
                                    Text(
                                        text = currentItem.duration,
                                        fontSize = 12.sp,
                                        color = GoldAccent,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // Large Side-by-Side Comparison Container
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            // BEFORE
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Slate900)
                                    .border(1.dp, Slate800, RoundedCornerShape(12.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                if (currentItem.beforeImageUri.isNotBlank()) {
                                    AsyncImage(
                                        model = currentItem.beforeImageUri,
                                        contentDescription = "Before image",
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier.fillMaxSize()
                                    )
                                } else {
                                    Icon(Icons.Default.PhotoCamera, contentDescription = null, tint = Slate600)
                                }
                                Surface(
                                    shape = RoundedCornerShape(bottomEnd = 8.dp),
                                    color = Color.Black.copy(alpha = 0.85f),
                                    modifier = Modifier.align(Alignment.TopStart)
                                ) {
                                    Text(
                                        text = "BEFORE",
                                        fontWeight = FontWeight.Black,
                                        fontSize = 11.sp,
                                        color = Color.White,
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                                    )
                                }
                            }

                            // AFTER
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Slate900)
                                    .border(1.5.dp, GoldAccent.copy(alpha = 0.6f), RoundedCornerShape(12.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                if (currentItem.afterImageUri.isNotBlank()) {
                                    AsyncImage(
                                        model = currentItem.afterImageUri,
                                        contentDescription = "After image",
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier.fillMaxSize()
                                    )
                                } else {
                                    Icon(Icons.Default.FitnessCenter, contentDescription = null, tint = GoldAccent)
                                }
                                Surface(
                                    shape = RoundedCornerShape(bottomEnd = 8.dp),
                                    color = GoldAccent,
                                    modifier = Modifier.align(Alignment.TopStart)
                                ) {
                                    Text(
                                        text = "AFTER",
                                        fontWeight = FontWeight.Black,
                                        fontSize = 11.sp,
                                        color = Slate950,
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                                    )
                                }
                            }
                        }

                        if (currentItem.description.isNotBlank()) {
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = currentItem.description,
                                fontSize = 13.sp,
                                color = Slate300
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // Navigation arrows (Prev / Next)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            OutlinedButton(
                                onClick = {
                                    if (currentIndex > 0) currentIndex--
                                },
                                enabled = currentIndex > 0,
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = Slate200)
                            ) {
                                Icon(Icons.Default.ChevronLeft, contentDescription = "Previous")
                                Text("Previous", fontSize = 12.sp)
                            }

                            Text(
                                text = "${currentIndex + 1} of ${filteredList.size}",
                                fontSize = 12.sp,
                                color = Slate400
                            )

                            OutlinedButton(
                                onClick = {
                                    if (currentIndex < filteredList.size - 1) currentIndex++
                                },
                                enabled = currentIndex < filteredList.size - 1,
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = Slate200)
                            ) {
                                Text("Next", fontSize = 12.sp)
                                Icon(Icons.Default.ChevronRight, contentDescription = "Next")
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Mandatory disclaimer
                Text(
                    text = disclaimerText,
                    fontSize = 10.sp,
                    lineHeight = 14.sp,
                    color = Slate500,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
