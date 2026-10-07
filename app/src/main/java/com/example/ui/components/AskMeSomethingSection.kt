package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@Composable
fun AskMeSomethingSection(
    name: String,
    onNameChange: (String) -> Unit,
    phone: String,
    onPhoneChange: (String) -> Unit,
    question: String,
    onQuestionChange: (String) -> Unit,
    message: String,
    onMessageChange: (String) -> Unit,
    errorMessage: String?,
    onSendQuestionClick: () -> Unit,
    onDirectWhatsAppClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .testTag("ask_me_something_section"),
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
                text = "INQUIRY & SUPPORT",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp,
                color = CyanAccent,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = "Ask Me Something",
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Slate50,
                modifier = Modifier.padding(bottom = 2.dp)
            )

            Text(
                text = "Have a question? We're here to help.",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = GoldAccent,
                modifier = Modifier.padding(bottom = 6.dp)
            )

            Text(
                text = "Submit your question below. It will open in WhatsApp so you can converse directly with our team.",
                fontSize = 12.sp,
                color = Slate400,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (errorMessage != null) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.errorContainer,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                ) {
                    Text(
                        text = errorMessage,
                        color = MaterialTheme.colorScheme.onErrorContainer,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }

            // Name Field (Required)
            OutlinedTextField(
                value = name,
                onValueChange = onNameChange,
                label = { Text("Your Name *") },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = CyanAccent) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("enquiry_name_input"),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = CyanAccent,
                    unfocusedBorderColor = Slate700,
                    focusedLabelColor = CyanAccent,
                    unfocusedLabelColor = Slate400,
                    focusedTextColor = Slate50,
                    unfocusedTextColor = Slate100
                ),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Phone Number Field (Required)
            OutlinedTextField(
                value = phone,
                onValueChange = onPhoneChange,
                label = { Text("Phone Number *") },
                leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null, tint = CyanAccent) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("enquiry_phone_input"),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = CyanAccent,
                    unfocusedBorderColor = Slate700,
                    focusedLabelColor = CyanAccent,
                    unfocusedLabelColor = Slate400,
                    focusedTextColor = Slate50,
                    unfocusedTextColor = Slate100
                ),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Your Question (Required)
            OutlinedTextField(
                value = question,
                onValueChange = onQuestionChange,
                label = { Text("Your Question *") },
                placeholder = { Text("e.g. What are your membership packages?") },
                leadingIcon = { Icon(Icons.Default.HelpOutline, contentDescription = null, tint = CyanAccent) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("enquiry_question_input"),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = CyanAccent,
                    unfocusedBorderColor = Slate700,
                    focusedLabelColor = CyanAccent,
                    unfocusedLabelColor = Slate400,
                    focusedTextColor = Slate50,
                    unfocusedTextColor = Slate100
                ),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Message (Optional details)
            OutlinedTextField(
                value = message,
                onValueChange = onMessageChange,
                label = { Text("Message (Optional)") },
                placeholder = { Text("Any specific goals or preferred timings...") },
                minLines = 3,
                maxLines = 4,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("enquiry_message_input"),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = CyanAccent,
                    unfocusedBorderColor = Slate700,
                    focusedLabelColor = CyanAccent,
                    unfocusedLabelColor = Slate400,
                    focusedTextColor = Slate50,
                    unfocusedTextColor = Slate100
                ),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Notice about WhatsApp
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = Slate850,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = WhatsAppGreen,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "SEND QUESTION opens WhatsApp with your enquiry prefilled so you can send it to our team.",
                        fontSize = 11.sp,
                        color = Slate300
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Action Buttons
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = onSendQuestionClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("send_question_button"),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CyanPrimary,
                        contentColor = Color.White
                    )
                ) {
                    Icon(Icons.Default.Send, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("SEND QUESTION", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                }

                OutlinedButton(
                    onClick = onDirectWhatsAppClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp)
                        .testTag("ask_on_whatsapp_button"),
                    shape = RoundedCornerShape(12.dp),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = Brush.horizontalGradient(listOf(WhatsAppGreen, WhatsAppGreen))
                    ),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = WhatsAppGreen
                    )
                ) {
                    Icon(Icons.Default.Chat, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("ASK ON WHATSAPP", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                }
            }
        }
    }
}
