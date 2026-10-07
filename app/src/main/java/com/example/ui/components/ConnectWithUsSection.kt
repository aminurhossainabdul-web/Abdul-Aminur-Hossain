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
fun ConnectWithUsSection(
    centerInfo: CenterInfo,
    onInstagramClick: () -> Unit,
    onFacebookClick: () -> Unit,
    onWhatsAppClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .testTag("connect_with_us_section"),
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
                text = "GET IN TOUCH",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp,
                color = CyanAccent,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = "Connect With Us",
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Slate50,
                modifier = Modifier.padding(bottom = 6.dp)
            )

            Text(
                text = "Reach out through your preferred platform for quick responses and updates.",
                fontSize = 13.sp,
                color = Slate400,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // 1. Instagram Card
                ContactSocialCard(
                    title = "Instagram",
                    handle = centerInfo.instagramHandle,
                    buttonText = "Follow on Instagram",
                    iconColor = Color(0xFFE1306C),
                    icon = Icons.Default.CameraAlt,
                    onClick = onInstagramClick,
                    testTag = "connect_instagram_button"
                )

                // 2. Facebook Card
                ContactSocialCard(
                    title = "Facebook",
                    handle = centerInfo.facebookLabel,
                    buttonText = "Follow on Facebook",
                    iconColor = FacebookBlue,
                    icon = Icons.Default.ThumbUp,
                    onClick = onFacebookClick,
                    testTag = "connect_facebook_button"
                )

                // 3. WhatsApp Card
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = Slate850,
                    border = CardDefaults.outlinedCardBorder().copy(brush = Brush.horizontalGradient(listOf(WhatsAppGreen.copy(alpha = 0.5f), Slate700))),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(42.dp)
                                    .clip(CircleShape)
                                    .background(WhatsAppGreen),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Chat,
                                    contentDescription = "WhatsApp",
                                    tint = Color.White,
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = "WhatsApp",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Slate100
                                )
                                Text(
                                    text = centerInfo.whatsAppNumber,
                                    fontSize = 13.sp,
                                    color = Slate300
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                onClick = onWhatsAppClick,
                                modifier = Modifier
                                    .weight(1f)
                                    .height(38.dp)
                                    .testTag("connect_whatsapp_chat_button"),
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = WhatsAppGreen)
                            ) {
                                Text("Chat on WhatsApp", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }

                            FilledTonalButton(
                                onClick = onWhatsAppClick,
                                modifier = Modifier
                                    .weight(1f)
                                    .height(38.dp)
                                    .testTag("connect_whatsapp_send_msg_button"),
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.filledTonalButtonColors(
                                    containerColor = Slate800,
                                    contentColor = Slate200
                                )
                            ) {
                                Text("Send a Message", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ContactSocialCard(
    title: String,
    handle: String,
    buttonText: String,
    iconColor: Color,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit,
    testTag: String
) {
    Surface(
        shape = RoundedCornerShape(14.dp),
        color = Slate850,
        border = CardDefaults.outlinedCardBorder().copy(brush = Brush.horizontalGradient(listOf(Slate800, Slate700))),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(iconColor.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = title,
                        tint = iconColor,
                        modifier = Modifier.size(22.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate100
                    )
                    Text(
                        text = handle,
                        fontSize = 12.sp,
                        color = Slate400
                    )
                }
            }

            Button(
                onClick = onClick,
                modifier = Modifier
                    .height(36.dp)
                    .testTag(testTag),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Slate800,
                    contentColor = Slate100
                )
            ) {
                Text(buttonText, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}
