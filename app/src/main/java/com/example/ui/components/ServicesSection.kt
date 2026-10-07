package com.example.ui.components

import androidx.compose.foundation.background
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
import com.example.data.model.FitnessService
import com.example.ui.theme.*

@Composable
fun ServicesSection(
    services: List<FitnessService>,
    onEnquireService: (FitnessService) -> Unit,
    modifier: Modifier = Modifier
) {
    val publishedServices = services.filter { it.isPublished }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .testTag("services_section"),
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
                text = "WHAT WE OFFER",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp,
                color = CyanAccent,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = "Our Services",
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Slate50,
                modifier = Modifier.padding(bottom = 6.dp)
            )

            Text(
                text = "Structured fitness regimens guided by verified principles.",
                fontSize = 13.sp,
                color = Slate400,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (publishedServices.isEmpty()) {
                // Neutral invitation to enquire on WhatsApp when no services configured
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = Slate850,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Chat,
                            contentDescription = null,
                            tint = WhatsAppGreen,
                            modifier = Modifier.size(36.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Enquire on WhatsApp",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Slate100
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Connect directly with New Life Fitness Center to explore current training slots, plans, and fitness guidance.",
                            fontSize = 12.sp,
                            color = Slate400,
                            modifier = Modifier.padding(bottom = 14.dp)
                        )
                        Button(
                            onClick = {
                                onEnquireService(
                                    FitnessService("inquiry", "General Services", "fitness", "General training enquiry", "", true)
                                )
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = WhatsAppGreen),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text("Chat on WhatsApp", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            } else {
                BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                    val isWide = maxWidth >= 600.dp
                    if (isWide) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            publishedServices.chunked(2).forEach { rowPair ->
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    rowPair.forEach { service ->
                                        Box(modifier = Modifier.weight(1f)) {
                                            ServiceCardItem(
                                                service = service,
                                                onLearnMore = { onEnquireService(service) }
                                            )
                                        }
                                    }
                                    if (rowPair.size == 1) {
                                        Spacer(modifier = Modifier.weight(1f))
                                    }
                                }
                            }
                        }
                    } else {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            publishedServices.forEach { service ->
                                ServiceCardItem(
                                    service = service,
                                    onLearnMore = { onEnquireService(service) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ServiceCardItem(
    service: FitnessService,
    onLearnMore: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Surface(
        shape = RoundedCornerShape(14.dp),
        color = Slate850,
        border = CardDefaults.outlinedCardBorder().copy(brush = Brush.horizontalGradient(listOf(Slate800, Slate700))),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("service_card_${service.id}")
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Slate800),
                    contentAlignment = Alignment.Center
                ) {
                    val iconVector = when (service.iconKey.lowercase()) {
                        "burn", "fire" -> Icons.Default.LocalFireDepartment
                        "person", "personal" -> Icons.Default.Person
                        "bolt", "strength" -> Icons.Default.Bolt
                        "run", "cardio" -> Icons.Default.DirectionsRun
                        "nutrition", "diet" -> Icons.Default.Restaurant
                        else -> Icons.Default.FitnessCenter
                    }
                    Icon(
                        imageVector = iconVector,
                        contentDescription = null,
                        tint = CyanAccent,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = service.name,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate100
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = service.shortDescription,
                        fontSize = 12.sp,
                        color = Slate400,
                        lineHeight = 16.sp
                    )
                }
            }

            if (expanded && service.details.isNotBlank()) {
                Spacer(modifier = Modifier.height(10.dp))
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Slate900,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = service.details,
                        fontSize = 12.sp,
                        color = Slate200,
                        lineHeight = 17.sp,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (service.details.isNotBlank()) {
                    TextButton(
                        onClick = { expanded = !expanded },
                        colors = ButtonDefaults.textButtonColors(contentColor = Slate400)
                    ) {
                        Text(if (expanded) "Show Less" else "View Details", fontSize = 11.sp)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }

                FilledTonalButton(
                    onClick = onLearnMore,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = CyanPrimary.copy(alpha = 0.25f),
                        contentColor = CyanAccent
                    ),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                    modifier = Modifier.height(34.dp)
                ) {
                    Icon(Icons.Default.Chat, contentDescription = null, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Learn More", fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}
