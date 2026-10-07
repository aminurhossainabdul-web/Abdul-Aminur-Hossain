package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.data.model.BeforeAfterResult
import com.example.ui.theme.*

@Composable
fun ResultsSection(
    results: List<BeforeAfterResult>,
    onSelectResult: (BeforeAfterResult) -> Unit,
    onViewMoreClick: () -> Unit,
    onWhatsAppClick: () -> Unit,
    disclaimerText: String,
    modifier: Modifier = Modifier
) {
    val publishedResults = results.filter { it.isPublished && it.hasOwnerPermission }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .testTag("results_section"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Slate900),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = Brush.verticalGradient(listOf(GoldAccent.copy(alpha = 0.4f), Slate850))
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            // Main Headings
            Text(
                text = "REAL RESULTS",
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = 1.2.sp,
                color = Slate50,
                modifier = Modifier.padding(bottom = 2.dp)
            )

            Text(
                text = "Before & After",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = GoldAccent,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Authentic client transformations achieved through consistent discipline.",
                fontSize = 13.sp,
                color = Slate400,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (publishedResults.isEmpty()) {
                // Honest, non-fabricated state when no verified photos are entered
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = Slate850,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(Slate800),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.VerifiedUser,
                                contentDescription = null,
                                tint = GoldAccent,
                                modifier = Modifier.size(26.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Genuine Transformations Only",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Slate100
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "In compliance with our strict authenticity standards, only owner-verified before & after photographs with explicit client consent are displayed here.",
                            fontSize = 12.sp,
                            color = Slate400,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(bottom = 14.dp)
                        )
                        Button(
                            onClick = onWhatsAppClick,
                            colors = ButtonDefaults.buttonColors(containerColor = WhatsAppGreen),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Icon(Icons.Default.Chat, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Ask About Results on WhatsApp", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        }
                    }
                }
            } else {
                // Render published before & after cards
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    publishedResults.take(3).forEach { item ->
                        BeforeAfterCard(
                            result = item,
                            onClick = { onSelectResult(item) }
                        )
                    }

                    if (publishedResults.size > 1) {
                        OutlinedButton(
                            onClick = onViewMoreClick,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(42.dp),
                            shape = RoundedCornerShape(10.dp),
                            border = CardDefaults.outlinedCardBorder().copy(brush = Brush.horizontalGradient(listOf(Slate700, Slate600))),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Slate200)
                        ) {
                            Icon(Icons.Default.Visibility, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("View More Results", fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Mandatory Disclaimer Box - exact wording
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = Slate950.copy(alpha = 0.7f),
                border = CardDefaults.outlinedCardBorder().copy(brush = Brush.horizontalGradient(listOf(Slate800, Slate800))),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Disclaimer",
                        tint = Slate400,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = disclaimerText,
                        fontSize = 11.sp,
                        lineHeight = 15.sp,
                        color = Slate400
                    )
                }
            }
        }
    }
}

@Composable
fun BeforeAfterCard(
    result: BeforeAfterResult,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(14.dp),
        color = Slate850,
        border = CardDefaults.outlinedCardBorder().copy(brush = Brush.horizontalGradient(listOf(Slate800, Slate700))),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            // Header Info: Title, Category, Duration
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = result.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Slate100
                )
                if (result.duration.isNotBlank()) {
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = Slate800,
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        Text(
                            text = result.duration,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = GoldAccent,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }
                }
            }

            if (result.category.isNotBlank()) {
                Text(
                    text = result.category,
                    fontSize = 11.sp,
                    color = CyanAccent,
                    modifier = Modifier.padding(top = 2.dp, bottom = 10.dp)
                )
            }

            // Before / After Dual Image Container
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // BEFORE Column
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Slate900)
                        .border(1.dp, Slate800, RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    if (result.beforeImageUri.isNotBlank()) {
                        AsyncImage(
                            model = result.beforeImageUri,
                            contentDescription = "Before transformation",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(Icons.Default.History, contentDescription = null, tint = Slate600)
                            Text("Photo", fontSize = 11.sp, color = Slate500)
                        }
                    }

                    // BEFORE Badge
                    Surface(
                        shape = RoundedCornerShape(bottomEnd = 8.dp),
                        color = Color.Black.copy(alpha = 0.8f),
                        modifier = Modifier.align(Alignment.TopStart)
                    ) {
                        Text(
                            text = "BEFORE",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Black,
                            letterSpacing = 1.sp,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                // AFTER Column
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Slate900)
                        .border(1.dp, GoldAccent.copy(alpha = 0.5f), RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    if (result.afterImageUri.isNotBlank()) {
                        AsyncImage(
                            model = result.afterImageUri,
                            contentDescription = "After transformation",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(Icons.Default.FitnessCenter, contentDescription = null, tint = GoldAccent)
                            Text("Photo", fontSize = 11.sp, color = Slate400)
                        }
                    }

                    // AFTER Badge
                    Surface(
                        shape = RoundedCornerShape(bottomEnd = 8.dp),
                        color = GoldAccent,
                        modifier = Modifier.align(Alignment.TopStart)
                    ) {
                        Text(
                            text = "AFTER",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Black,
                            letterSpacing = 1.sp,
                            color = Slate950,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }

            if (result.description.isNotBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = result.description,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    color = Slate300
                )
            }
        }
    }
}
