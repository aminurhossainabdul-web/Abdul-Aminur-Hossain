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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Testimonial
import com.example.ui.theme.*

@Composable
fun TestimonialsSection(
    testimonials: List<Testimonial>,
    onWhatsAppClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val approvedTestimonials = testimonials.filter { it.isApproved }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .testTag("testimonials_section"),
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
                text = "COMMUNITY VOICES",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp,
                color = CyanAccent,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = "What Our Clients Say",
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Slate50,
                modifier = Modifier.padding(bottom = 6.dp)
            )

            Text(
                text = "Genuine reflections from members of the New Life Fitness community.",
                fontSize = 13.sp,
                color = Slate400,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (approvedTestimonials.isEmpty()) {
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
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(Slate800),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.RateReview,
                                contentDescription = null,
                                tint = CyanAccent,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Verified Client Stories",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Slate100
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "To preserve complete authenticity, client testimonials are only published upon direct owner verification and member consent. Reach out to hear directly from our gym community.",
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
                            Text("Chat with New Life Fitness", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        }
                    }
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    approvedTestimonials.forEach { item ->
                        Surface(
                            shape = RoundedCornerShape(14.dp),
                            color = Slate850,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = item.clientName,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = Slate100
                                    )
                                    Row {
                                        repeat(item.rating) {
                                            Icon(
                                                imageVector = Icons.Default.Star,
                                                contentDescription = null,
                                                tint = GoldAccent,
                                                modifier = Modifier.size(14.dp)
                                            )
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = "\"${item.testimonial}\"",
                                    fontSize = 12.sp,
                                    lineHeight = 17.sp,
                                    color = Slate300
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
