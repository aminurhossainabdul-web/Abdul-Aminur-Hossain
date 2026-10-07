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
import com.example.data.model.CenterInfo
import com.example.ui.theme.*

@Composable
fun ContactSection(
    centerInfo: CenterInfo,
    onCallClick: () -> Unit,
    onWhatsAppClick: () -> Unit,
    onInstagramClick: () -> Unit,
    onFacebookClick: () -> Unit,
    onDirectionsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .testTag("contact_section"),
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
                text = "LOCATION & REACH",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp,
                color = CyanAccent,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = "Contact New Life Fitness Center",
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Slate50,
                modifier = Modifier.padding(bottom = 6.dp)
            )

            Text(
                text = "Get in touch directly with our leadership and training staff.",
                fontSize = 13.sp,
                color = Slate400,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                val isWide = maxWidth >= 600.dp
                if (isWide) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // Left Column: Phone & Socials
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            // Primary Phone & Direct Actions
                            Surface(
                                shape = RoundedCornerShape(14.dp),
                                color = Slate850,
                                border = CardDefaults.outlinedCardBorder().copy(brush = Brush.horizontalGradient(listOf(CyanPrimary.copy(alpha = 0.4f), Slate800))),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(14.dp)) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Box(
                                            modifier = Modifier
                                                .size(38.dp)
                                                .clip(CircleShape)
                                                .background(CyanPrimary),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Phone,
                                                contentDescription = null,
                                                tint = Color.White,
                                                modifier = Modifier.size(18.dp)
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(12.dp))
                                        Column {
                                            Text(
                                                text = "Primary Phone",
                                                fontSize = 11.sp,
                                                fontWeight = FontWeight.SemiBold,
                                                color = Slate400
                                            )
                                            Text(
                                                text = centerInfo.phone,
                                                fontSize = 15.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Slate100
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(12.dp))

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        Button(
                                            onClick = onCallClick,
                                            modifier = Modifier
                                                .weight(1f)
                                                .height(38.dp)
                                                .testTag("contact_call_now_button"),
                                            shape = RoundedCornerShape(8.dp),
                                            colors = ButtonDefaults.buttonColors(containerColor = CyanPrimary)
                                        ) {
                                            Icon(Icons.Default.Call, contentDescription = null, modifier = Modifier.size(14.dp))
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text("CALL NOW", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                        }

                                        Button(
                                            onClick = onWhatsAppClick,
                                            modifier = Modifier
                                                .weight(1f)
                                                .height(38.dp)
                                                .testTag("contact_whatsapp_button"),
                                            shape = RoundedCornerShape(8.dp),
                                            colors = ButtonDefaults.buttonColors(containerColor = WhatsAppGreen)
                                        ) {
                                            Icon(Icons.Default.Chat, contentDescription = null, modifier = Modifier.size(14.dp))
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text("WHATSAPP", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                        }
                                    }
                                }
                            }

                            // Social Channels Quick Buttons
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                FilledTonalButton(
                                    onClick = onInstagramClick,
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(38.dp)
                                        .testTag("contact_instagram_link_button"),
                                    colors = ButtonDefaults.filledTonalButtonColors(
                                        containerColor = Slate800,
                                        contentColor = Slate200
                                    )
                                ) {
                                    Icon(Icons.Default.CameraAlt, contentDescription = null, modifier = Modifier.size(14.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("INSTAGRAM", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                }

                                FilledTonalButton(
                                    onClick = onFacebookClick,
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(38.dp)
                                        .testTag("contact_facebook_link_button"),
                                    colors = ButtonDefaults.filledTonalButtonColors(
                                        containerColor = Slate800,
                                        contentColor = Slate200
                                    )
                                ) {
                                    Icon(Icons.Default.ThumbUp, contentDescription = null, modifier = Modifier.size(14.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("FACEBOOK", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                        }

                        // Right Column: Location & Hours
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            // Address & Location Status Card
                            Surface(
                                shape = RoundedCornerShape(14.dp),
                                color = Slate850,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(14.dp)) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = Icons.Default.LocationOn,
                                            contentDescription = null,
                                            tint = GoldAccent,
                                            modifier = Modifier.size(20.dp)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = "Gym Location",
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Slate100
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = centerInfo.address,
                                        fontSize = 12.sp,
                                        color = Slate300
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = if (centerInfo.mapsUrl.isNotBlank()) "Google Maps directions available." else "Direct map pin awaiting owner link. Tap below to request full directions on WhatsApp.",
                                        fontSize = 11.sp,
                                        color = Slate400
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                    OutlinedButton(
                                        onClick = onDirectionsClick,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(36.dp)
                                            .testTag("contact_directions_button"),
                                        shape = RoundedCornerShape(8.dp),
                                        colors = ButtonDefaults.outlinedButtonColors(contentColor = GoldAccent),
                                        border = ButtonDefaults.outlinedButtonBorder.copy(
                                            brush = Brush.horizontalGradient(listOf(GoldAccent, GoldAccent))
                                        )
                                    ) {
                                        Icon(Icons.Default.Directions, contentDescription = null, modifier = Modifier.size(16.dp))
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = if (centerInfo.mapsUrl.isNotBlank()) "GET DIRECTIONS" else "REQUEST LOCATION ON WHATSAPP",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }

                            // Opening Hours Card
                            Surface(
                                shape = RoundedCornerShape(14.dp),
                                color = Slate850,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(14.dp)) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = Icons.Default.Schedule,
                                            contentDescription = null,
                                            tint = CyanAccent,
                                            modifier = Modifier.size(20.dp)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = "Opening Hours",
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Slate100
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = centerInfo.openingHours,
                                        fontSize = 12.sp,
                                        color = Slate300
                                    )
                                    Text(
                                        text = "Contact us via WhatsApp (+91 9933521448) to confirm today's training schedule.",
                                        fontSize = 11.sp,
                                        color = Slate400,
                                        modifier = Modifier.padding(top = 4.dp)
                                    )
                                }
                            }
                        }
                    }
                } else {
                    // Compact Phone Layout
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Primary Phone & Direct Actions
                        Surface(
                            shape = RoundedCornerShape(14.dp),
                            color = Slate850,
                            border = CardDefaults.outlinedCardBorder().copy(brush = Brush.horizontalGradient(listOf(CyanPrimary.copy(alpha = 0.4f), Slate800))),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Box(
                                        modifier = Modifier
                                            .size(38.dp)
                                            .clip(CircleShape)
                                            .background(CyanPrimary),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Phone,
                                            contentDescription = null,
                                            tint = Color.White,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Column {
                                        Text(
                                            text = "Primary Phone",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = Slate400
                                        )
                                        Text(
                                            text = centerInfo.phone,
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Slate100
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(12.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Button(
                                        onClick = onCallClick,
                                        modifier = Modifier
                                            .weight(1f)
                                            .height(38.dp)
                                            .testTag("contact_call_now_button"),
                                        shape = RoundedCornerShape(8.dp),
                                        colors = ButtonDefaults.buttonColors(containerColor = CyanPrimary)
                                    ) {
                                        Icon(Icons.Default.Call, contentDescription = null, modifier = Modifier.size(14.dp))
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text("CALL NOW", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                    }

                                    Button(
                                        onClick = onWhatsAppClick,
                                        modifier = Modifier
                                            .weight(1f)
                                            .height(38.dp)
                                            .testTag("contact_whatsapp_button"),
                                        shape = RoundedCornerShape(8.dp),
                                        colors = ButtonDefaults.buttonColors(containerColor = WhatsAppGreen)
                                    ) {
                                        Icon(Icons.Default.Chat, contentDescription = null, modifier = Modifier.size(14.dp))
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text("WHATSAPP", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                        }

                        // Address & Location Status Card
                        Surface(
                            shape = RoundedCornerShape(14.dp),
                            color = Slate850,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.LocationOn,
                                        contentDescription = null,
                                        tint = GoldAccent,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "Gym Location",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Slate100
                                    )
                                }
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = centerInfo.address,
                                    fontSize = 12.sp,
                                    color = Slate300
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = if (centerInfo.mapsUrl.isNotBlank()) "Google Maps directions available." else "Direct map pin awaiting owner link. Tap below to request full directions on WhatsApp.",
                                    fontSize = 11.sp,
                                    color = Slate400
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                OutlinedButton(
                                    onClick = onDirectionsClick,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(36.dp)
                                        .testTag("contact_directions_button"),
                                    shape = RoundedCornerShape(8.dp),
                                    colors = ButtonDefaults.outlinedButtonColors(contentColor = GoldAccent),
                                    border = ButtonDefaults.outlinedButtonBorder.copy(
                                        brush = Brush.horizontalGradient(listOf(GoldAccent, GoldAccent))
                                    )
                                ) {
                                    Icon(Icons.Default.Directions, contentDescription = null, modifier = Modifier.size(16.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = if (centerInfo.mapsUrl.isNotBlank()) "GET DIRECTIONS" else "REQUEST LOCATION ON WHATSAPP",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }

                        // Opening Hours Card
                        Surface(
                            shape = RoundedCornerShape(14.dp),
                            color = Slate850,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.Schedule,
                                        contentDescription = null,
                                        tint = CyanAccent,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "Opening Hours",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Slate100
                                    )
                                }
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = centerInfo.openingHours,
                                    fontSize = 12.sp,
                                    color = Slate300
                                )
                                Text(
                                    text = "Contact us via WhatsApp (+91 9933521448) to confirm today's training schedule.",
                                    fontSize = 11.sp,
                                    color = Slate400,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }
                        }

                        // Social Channels Quick Buttons
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            FilledTonalButton(
                                onClick = onInstagramClick,
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(36.dp)
                                    .testTag("contact_instagram_link_button"),
                                colors = ButtonDefaults.filledTonalButtonColors(
                                    containerColor = Slate800,
                                    contentColor = Slate200
                                )
                            ) {
                                Icon(Icons.Default.CameraAlt, contentDescription = null, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("INSTAGRAM", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            }

                            FilledTonalButton(
                                onClick = onFacebookClick,
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(36.dp)
                                    .testTag("contact_facebook_link_button"),
                                colors = ButtonDefaults.filledTonalButtonColors(
                                    containerColor = Slate800,
                                    contentColor = Slate200
                                )
                            ) {
                                Icon(Icons.Default.ThumbUp, contentDescription = null, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("FACEBOOK", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }
    }
}
