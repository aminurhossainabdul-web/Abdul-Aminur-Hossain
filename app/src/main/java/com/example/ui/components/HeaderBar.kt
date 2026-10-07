package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.NavSection
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderBar(
    onWhatsAppClick: () -> Unit,
    onOwnerToggle: () -> Unit,
    isOwnerMode: Boolean,
    onNavSelect: (NavSection) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = Slate900.copy(alpha = 0.95f),
        tonalElevation = 6.dp,
        border = CardDefaults.outlinedCardBorder().copy(brush = Brush.verticalGradient(listOf(Slate800, Color.Transparent)))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
        ) {
            // Main App Header Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Logo & Title
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable { onNavSelect(NavSection.HOME) }
                        .testTag("header_brand_button")
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.linearGradient(
                                    listOf(CyanAccent, CyanPrimary, Slate900)
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.FitnessCenter,
                            contentDescription = "New Life Fitness Logo",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "NEW LIFE",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Black,
                            letterSpacing = 1.sp,
                            color = Slate50
                        )
                        Text(
                            text = "FITNESS CENTER",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.5.sp,
                            color = CyanAccent
                        )
                    }
                }

                // Header Actions: WhatsApp CTA + Owner Mode Toggle
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // WhatsApp Quick Pill
                    Button(
                        onClick = onWhatsAppClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = WhatsAppGreen,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(20.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                        modifier = Modifier
                            .height(36.dp)
                            .testTag("header_whatsapp_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Chat,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "WhatsApp",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    // Owner Mode / CMS Toggle Button
                    IconButton(
                        onClick = onOwnerToggle,
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(if (isOwnerMode) GoldAccent.copy(alpha = 0.2f) else Slate800)
                            .testTag("header_owner_mode_toggle")
                    ) {
                        Icon(
                            imageVector = if (isOwnerMode) Icons.Default.EditNote else Icons.Default.Settings,
                            contentDescription = "Owner Content Management",
                            tint = if (isOwnerMode) GoldAccent else Slate400,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            // Horizontal Navigation Tabs
            val navSections = listOf(
                NavSection.HOME,
                NavSection.ABOUT,
                NavSection.SERVICES,
                NavSection.RESULTS,
                NavSection.WHY_US,
                NavSection.TESTIMONIALS,
                NavSection.ASK,
                NavSection.CONNECT,
                NavSection.CONTACT,
                NavSection.FAQ
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp),
                contentPadding = PaddingValues(horizontal = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                items(navSections) { section ->
                    SuggestionChip(
                        onClick = { onNavSelect(section) },
                        label = {
                            Text(
                                text = section.label,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                color = Slate200
                            )
                        },
                        colors = SuggestionChipDefaults.suggestionChipColors(
                            containerColor = Slate850,
                            labelColor = Slate200
                        ),
                        border = SuggestionChipDefaults.suggestionChipBorder(
                            enabled = true,
                            borderColor = Slate800
                        ),
                        modifier = Modifier.height(30.dp)
                    )
                }
            }
        }
    }
}
