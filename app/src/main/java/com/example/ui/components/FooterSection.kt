package com.example.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.CenterInfo
import com.example.data.model.NavSection
import com.example.ui.theme.*
import java.util.Calendar

@Composable
fun FooterSection(
    centerInfo: CenterInfo,
    onNavSelect: (NavSection) -> Unit,
    onInstagramClick: () -> Unit,
    onFacebookClick: () -> Unit,
    onWhatsAppClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .testTag("footer_section"),
        color = Slate950,
        border = CardDefaults.outlinedCardBorder().copy(brush = Brush.verticalGradient(listOf(Slate850, Slate950)))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 24.dp)
        ) {
            // Brand & Tagline
            Text(
                text = centerInfo.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = 1.sp,
                color = Slate50
            )

            Text(
                text = centerInfo.tagline,
                fontSize = 12.sp,
                color = CyanAccent,
                modifier = Modifier.padding(top = 2.dp, bottom = 8.dp)
            )

            Text(
                text = "Committed to delivering disciplined, authentic fitness guidance for everyday wellness.",
                fontSize = 12.sp,
                lineHeight = 16.sp,
                color = Slate400,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Divider(color = Slate850, thickness = 1.dp)

            Spacer(modifier = Modifier.height(16.dp))

            // Quick Links
            Text(
                text = "QUICK NAVIGATION",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.2.sp,
                color = Slate400,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            val links = listOf(
                "Home" to NavSection.HOME,
                "About Us" to NavSection.ABOUT,
                "Services" to NavSection.SERVICES,
                "Results" to NavSection.RESULTS,
                "Testimonials" to NavSection.TESTIMONIALS,
                "FAQ" to NavSection.FAQ,
                "Contact" to NavSection.CONTACT
            )

            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                links.forEach { (title, section) ->
                    Text(
                        text = title,
                        fontSize = 12.sp,
                        color = Slate300,
                        modifier = Modifier
                            .clickable { onNavSelect(section) }
                            .padding(vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Divider(color = Slate850, thickness = 1.dp)
            Spacer(modifier = Modifier.height(16.dp))

            // Contact & Social Details
            Text(
                text = "DIRECT CONTACT",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.2.sp,
                color = Slate400,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Phone: ${centerInfo.phone}",
                fontSize = 12.sp,
                color = Slate200,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = "Address: ${centerInfo.address}",
                fontSize = 12.sp,
                color = Slate400,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = "Hours: ${centerInfo.openingHours}",
                fontSize = 12.sp,
                color = Slate400,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Social Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = onInstagramClick,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f).height(36.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Slate200)
                ) {
                    Text("Instagram", fontSize = 11.sp)
                }

                OutlinedButton(
                    onClick = onFacebookClick,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f).height(36.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Slate200)
                ) {
                    Text("Facebook", fontSize = 11.sp)
                }

                OutlinedButton(
                    onClick = onWhatsAppClick,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f).height(36.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = WhatsAppGreen)
                ) {
                    Text("WhatsApp", fontSize = 11.sp)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Copyright
            Text(
                text = "© $currentYear New Life Fitness Center. All Rights Reserved.",
                fontSize = 11.sp,
                color = Slate500,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(48.dp)) // Padding for bottom floating actions
        }
    }
}
