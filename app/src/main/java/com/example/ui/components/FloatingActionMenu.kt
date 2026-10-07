package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.ui.theme.*

@Composable
fun FloatingActionMenu(
    onWhatsAppClick: () -> Unit,
    onCallClick: () -> Unit,
    onInstagramClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .padding(end = 16.dp, bottom = 16.dp)
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Expandable Secondary Actions (Call & Instagram)
        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn() + slideInVertically(initialOffsetY = { it / 2 }),
            exit = fadeOut() + slideOutVertically(targetOffsetY = { it / 2 })
        ) {
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Secondary 1: Call
                SmallFloatingActionButton(
                    onClick = {
                        expanded = false
                        onCallClick()
                    },
                    containerColor = Slate800,
                    contentColor = CyanAccent,
                    shape = CircleShape,
                    modifier = Modifier.testTag("floating_call_button")
                ) {
                    Icon(Icons.Default.Phone, contentDescription = "Call Center", modifier = Modifier.size(18.dp))
                }

                // Secondary 2: Instagram
                SmallFloatingActionButton(
                    onClick = {
                        expanded = false
                        onInstagramClick()
                    },
                    containerColor = Slate800,
                    contentColor = Color(0xFFE1306C),
                    shape = CircleShape,
                    modifier = Modifier.testTag("floating_instagram_button")
                ) {
                    Icon(Icons.Default.CameraAlt, contentDescription = "Instagram", modifier = Modifier.size(18.dp))
                }
            }
        }

        // Main Primary Floating WhatsApp Button with Toggle
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            // Expand / Collapse mini button
            IconButton(
                onClick = { expanded = !expanded },
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(Slate800)
            ) {
                Icon(
                    imageVector = if (expanded) Icons.Default.Close else Icons.Default.MoreVert,
                    contentDescription = "More quick contacts",
                    tint = Slate300,
                    modifier = Modifier.size(16.dp)
                )
            }

            // Big WhatsApp FAB
            FloatingActionButton(
                onClick = onWhatsAppClick,
                containerColor = WhatsAppGreen,
                contentColor = Color.White,
                shape = CircleShape,
                modifier = Modifier
                    .size(56.dp)
                    .testTag("floating_whatsapp_main_button")
            ) {
                Icon(
                    imageVector = Icons.Default.Chat,
                    contentDescription = "Chat on WhatsApp",
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}
