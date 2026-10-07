package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.CenterInfo
import com.example.ui.theme.*

@Composable
fun AboutSection(
    centerInfo: CenterInfo,
    onLearnMoreClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .testTag("about_section"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Slate900),
        border = CardDefaults.outlinedCardBorder().copy(brush = Brush.verticalGradient(listOf(Slate800, Slate850)))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            // Section Tag
            Text(
                text = "WHO WE ARE",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp,
                color = CyanAccent,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            // Section Heading
            Text(
                text = "About New Life Fitness Center",
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Slate50,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Abstract Branded Image Area
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        Brush.linearGradient(
                            listOf(Slate850, Slate800, Slate900)
                        )
                    )
                    .border(1.dp, Slate700, RoundedCornerShape(16.dp))
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(54.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.linearGradient(listOf(CyanAccent, CyanPrimary))
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.SportsGymnastics,
                            contentDescription = "Fitness Philosophy",
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Building Strength & Sustainable Wellness",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Slate200
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 6 Structured Approved Content Cards
            AboutPillarItem(
                title = "The Center",
                content = centerInfo.aboutTheCenter,
                icon = Icons.Default.Business
            )
            AboutPillarItem(
                title = "Our Approach",
                content = centerInfo.aboutApproach,
                icon = Icons.Default.Checklist
            )
            AboutPillarItem(
                title = "Health & Fitness Philosophy",
                content = centerInfo.aboutPhilosophy,
                icon = Icons.Default.SelfImprovement
            )
            AboutPillarItem(
                title = "Experience",
                content = centerInfo.aboutExperience,
                icon = Icons.Default.StarOutline
            )
            AboutPillarItem(
                title = "Professional Services",
                content = "Offering verified strength conditioning, weight management, and personal trainer attention.",
                icon = Icons.Default.FitnessCenter
            )
            AboutPillarItem(
                title = "Our Goal for Clients",
                content = centerInfo.aboutGoal,
                icon = Icons.Default.EmojiEvents
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Learn More Button (Directs to WhatsApp as per prompt specification)
            Button(
                onClick = onLearnMoreClick,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Slate800,
                    contentColor = CyanAccent
                ),
                border = CardDefaults.outlinedCardBorder().copy(brush = Brush.horizontalGradient(listOf(CyanPrimary, Slate700))),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
                    .testTag("about_learn_more_button")
            ) {
                Icon(Icons.Default.Chat, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Learn More On WhatsApp",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }
        }
    }
}

@Composable
private fun AboutPillarItem(
    title: String,
    content: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        color = Slate850,
        border = CardDefaults.outlinedCardBorder().copy(brush = Brush.horizontalGradient(listOf(Slate800, Slate800)))
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(Slate800),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = CyanAccent,
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = title,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Slate100
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = content,
                    fontSize = 12.sp,
                    lineHeight = 17.sp,
                    color = Slate400
                )
            }
        }
    }
}
