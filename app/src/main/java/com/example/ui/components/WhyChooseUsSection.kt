package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.WhyChooseUsPoint
import com.example.ui.theme.*

@Composable
fun WhyChooseUsSection(
    points: List<WhyChooseUsPoint>,
    modifier: Modifier = Modifier
) {
    val publishedPoints = points.filter { it.isPublished }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .testTag("why_us_section"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Slate900),
        border = CardDefaults.outlinedCardBorder().copy(brush = Brush.verticalGradient(listOf(Slate800, Slate850)))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = "OUR COMMITMENT",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp,
                color = CyanAccent,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = "Why Choose New Life Fitness Center?",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Slate50,
                modifier = Modifier.padding(bottom = 6.dp)
            )

            Text(
                text = "Our core values centered on disciplined fitness and dedicated client support.",
                fontSize = 13.sp,
                color = Slate400,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                publishedPoints.forEach { point ->
                    WhyPointItem(point = point)
                }
            }
        }
    }
}

@Composable
private fun WhyPointItem(point: WhyChooseUsPoint) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Slate850,
        border = CardDefaults.outlinedCardBorder().copy(brush = Brush.horizontalGradient(listOf(Slate800, Slate800))),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(if (point.isConfirmed) CyanPrimary.copy(alpha = 0.2f) else Slate800),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if (point.isConfirmed) Icons.Default.CheckCircle else Icons.Default.HelpOutline,
                    contentDescription = null,
                    tint = if (point.isConfirmed) CyanAccent else Slate400,
                    modifier = Modifier.size(18.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = point.title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate100
                    )
                    if (point.isConfirmed) {
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = Slate800
                        ) {
                            Text(
                                text = "Verified",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = CyanAccent,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = point.description,
                    fontSize = 12.sp,
                    lineHeight = 17.sp,
                    color = Slate400
                )
            }
        }
    }
}
