package com.example.ui.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.model.*
import com.example.ui.theme.*

@Composable
fun OwnerCmsDialog(
    centerInfo: CenterInfo,
    services: List<FitnessService>,
    results: List<BeforeAfterResult>,
    whyPoints: List<WhyChooseUsPoint>,
    testimonials: List<Testimonial>,
    faqs: List<FaqItem>,
    onUpdateCenterInfo: (CenterInfo) -> Unit,
    onUpdateService: (FitnessService) -> Unit,
    onSaveResult: (BeforeAfterResult) -> Unit,
    onDeleteResult: (String) -> Unit,
    onToggleWhyPoint: (String) -> Unit,
    onSaveTestimonial: (Testimonial) -> Unit,
    onDeleteTestimonial: (String) -> Unit,
    onSaveFaq: (FaqItem) -> Unit,
    onDismiss: () -> Unit
) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabTitles = listOf("Center Details", "Services (6)", "Results (B/A)", "Testimonials", "FAQs")

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Slate950
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding()
            ) {
                // Top Header
                Surface(
                    color = Slate900,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(GoldAccent.copy(alpha = 0.2f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.EditNote, contentDescription = null, tint = GoldAccent)
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text("Owner CMS Editor", fontWeight = FontWeight.Black, fontSize = 16.sp, color = Slate50)
                                Text("Content Management & Verification", fontSize = 11.sp, color = GoldAccent)
                            }
                        }

                        IconButton(
                            onClick = onDismiss,
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(Slate800)
                        ) {
                            Icon(Icons.Default.Close, contentDescription = "Close CMS", tint = Slate200)
                        }
                    }
                }

                // Scrollable Tabs
                ScrollableTabRow(
                    selectedTabIndex = selectedTab,
                    containerColor = Slate900,
                    contentColor = CyanAccent,
                    edgePadding = 12.dp
                ) {
                    tabTitles.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            text = { Text(title, fontSize = 12.sp, fontWeight = FontWeight.SemiBold) }
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    when (selectedTab) {
                        0 -> CenterDetailsEditor(centerInfo = centerInfo, onSave = onUpdateCenterInfo)
                        1 -> ServicesEditor(services = services, onUpdateService = onUpdateService)
                        2 -> ResultsEditor(results = results, onSaveResult = onSaveResult, onDeleteResult = onDeleteResult)
                        3 -> TestimonialsEditor(testimonials = testimonials, onSaveTestimonial = onSaveTestimonial, onDeleteTestimonial = onDeleteTestimonial)
                        4 -> FaqsEditor(faqs = faqs, onSaveFaq = onSaveFaq)
                    }
                }
            }
        }
    }
}

@Composable
private fun CenterDetailsEditor(
    centerInfo: CenterInfo,
    onSave: (CenterInfo) -> Unit
) {
    var address by remember { mutableStateOf(centerInfo.address) }
    var openingHours by remember { mutableStateOf(centerInfo.openingHours) }
    var mapsUrl by remember { mutableStateOf(centerInfo.mapsUrl) }
    var tagline by remember { mutableStateOf(centerInfo.tagline) }
    var heroIntro by remember { mutableStateOf(centerInfo.heroIntro) }
    var savedNotice by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text("Business Information & Physical Location", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Slate100)
            Text(
                "Update address and opening hours once confirmed. Unverified details can remain as 'Awaiting owner-provided...'",
                fontSize = 12.sp,
                color = Slate400,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        item {
            OutlinedTextField(
                value = address,
                onValueChange = { address = it; savedNotice = false },
                label = { Text("Physical Address") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 2,
                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = CyanAccent, unfocusedBorderColor = Slate700, focusedTextColor = Slate50, unfocusedTextColor = Slate100)
            )
        }

        item {
            OutlinedTextField(
                value = openingHours,
                onValueChange = { openingHours = it; savedNotice = false },
                label = { Text("Opening Hours & Batches") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 2,
                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = CyanAccent, unfocusedBorderColor = Slate700, focusedTextColor = Slate50, unfocusedTextColor = Slate100)
            )
        }

        item {
            OutlinedTextField(
                value = mapsUrl,
                onValueChange = { mapsUrl = it; savedNotice = false },
                label = { Text("Google Maps Link (Optional)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = CyanAccent, unfocusedBorderColor = Slate700, focusedTextColor = Slate50, unfocusedTextColor = Slate100)
            )
        }

        item {
            OutlinedTextField(
                value = tagline,
                onValueChange = { tagline = it; savedNotice = false },
                label = { Text("Tagline") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = CyanAccent, unfocusedBorderColor = Slate700, focusedTextColor = Slate50, unfocusedTextColor = Slate100)
            )
        }

        item {
            OutlinedTextField(
                value = heroIntro,
                onValueChange = { heroIntro = it; savedNotice = false },
                label = { Text("Hero Introduction Copy") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 2,
                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = CyanAccent, unfocusedBorderColor = Slate700, focusedTextColor = Slate50, unfocusedTextColor = Slate100)
            )
        }

        item {
            Button(
                onClick = {
                    onSave(
                        centerInfo.copy(
                            address = address,
                            openingHours = openingHours,
                            mapsUrl = mapsUrl,
                            tagline = tagline,
                            heroIntro = heroIntro
                        )
                    )
                    savedNotice = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
                    .testTag("cms_save_center_info_button"),
                colors = ButtonDefaults.buttonColors(containerColor = CyanPrimary)
            ) {
                Text("SAVE CENTER DETAILS", fontWeight = FontWeight.Bold)
            }
            if (savedNotice) {
                Text("✓ Details saved successfully", color = WhatsAppGreen, fontSize = 12.sp, modifier = Modifier.padding(top = 6.dp))
            }
        }
    }
}

@Composable
private fun ServicesEditor(
    services: List<FitnessService>,
    onUpdateService: (FitnessService) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text("Service Slots (6 Available)", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Slate100)
            Text(
                "Toggle 'Published' to show on public screen. Incomplete slots can be marked Draft to keep them hidden.",
                fontSize = 12.sp,
                color = Slate400
            )
        }

        items(services) { service ->
            var name by remember(service) { mutableStateOf(service.name) }
            var desc by remember(service) { mutableStateOf(service.shortDescription) }
            var isPublished by remember(service) { mutableStateOf(service.isPublished) }

            Surface(
                shape = RoundedCornerShape(12.dp),
                color = Slate900,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(service.id.uppercase(), fontWeight = FontWeight.Bold, color = CyanAccent, fontSize = 12.sp)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(if (isPublished) "Published" else "Draft (Hidden)", fontSize = 11.sp, color = if (isPublished) WhatsAppGreen else Slate400)
                            Spacer(modifier = Modifier.width(6.dp))
                            Switch(
                                checked = isPublished,
                                onCheckedChange = {
                                    isPublished = it
                                    onUpdateService(service.copy(name = name, shortDescription = desc, isPublished = it))
                                },
                                modifier = Modifier.height(24.dp)
                            )
                        }
                    }

                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Service Name") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Slate50, unfocusedTextColor = Slate100)
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    OutlinedTextField(
                        value = desc,
                        onValueChange = { desc = it },
                        label = { Text("Short Description") },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 2,
                        colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Slate50, unfocusedTextColor = Slate100)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            onUpdateService(service.copy(name = name, shortDescription = desc, isPublished = isPublished))
                        },
                        modifier = Modifier.align(Alignment.End),
                        colors = ButtonDefaults.buttonColors(containerColor = Slate800)
                    ) {
                        Text("Update Slot", fontSize = 11.sp)
                    }
                }
            }
        }
    }
}

@Composable
private fun ResultsEditor(
    results: List<BeforeAfterResult>,
    onSaveResult: (BeforeAfterResult) -> Unit,
    onDeleteResult: (String) -> Unit
) {
    var showAddDialog by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Genuine Before & After Gallery", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Slate100)
                    Text("Only publish authentic images with client consent.", fontSize = 12.sp, color = GoldAccent)
                }

                Button(
                    onClick = { showAddDialog = true },
                    colors = ButtonDefaults.buttonColors(containerColor = CyanPrimary)
                ) {
                    Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Add Result", fontSize = 12.sp)
                }
            }
        }

        if (results.isEmpty()) {
            item {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Slate900,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "No results registered yet. Use 'Add Result' to record a genuine client transformation with confirmed permission.",
                        color = Slate400,
                        fontSize = 13.sp,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }

        items(results) { item ->
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = Slate900,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(item.title, fontWeight = FontWeight.Bold, color = Slate100, fontSize = 14.sp)
                        Text("${item.category} • ${item.duration}", color = CyanAccent, fontSize = 11.sp)
                        Text(
                            text = if (item.isPublished) "Status: Published" else "Status: Draft",
                            color = if (item.isPublished) WhatsAppGreen else Slate400,
                            fontSize = 11.sp
                        )
                    }

                    Row {
                        IconButton(onClick = { onSaveResult(item.copy(isPublished = !item.isPublished)) }) {
                            Icon(
                                imageVector = if (item.isPublished) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = "Toggle publish",
                                tint = if (item.isPublished) WhatsAppGreen else Slate500
                            )
                        }
                        IconButton(onClick = { onDeleteResult(item.id) }) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red.copy(alpha = 0.8f))
                        }
                    }
                }
            }
        }
    }

    if (showAddDialog) {
        AddResultDialog(
            onDismiss = { showAddDialog = false },
            onSave = {
                onSaveResult(it)
                showAddDialog = false
            }
        )
    }
}

@Composable
private fun AddResultDialog(
    onDismiss: () -> Unit,
    onSave: (BeforeAfterResult) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("Strength & Conditioning") }
    var duration by remember { mutableStateOf("12 Weeks") }
    var description by remember { mutableStateOf("") }
    var beforeUri by remember { mutableStateOf("") }
    var afterUri by remember { mutableStateOf("") }
    var hasPermission by remember { mutableStateOf(true) }
    var isPublished by remember { mutableStateOf(true) }

    val beforePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) beforeUri = uri.toString()
    }

    val afterPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) afterUri = uri.toString()
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Genuine Transformation", fontWeight = FontWeight.Bold, color = Slate50) },
        text = {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                item {
                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text("Client Reference / Title *") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Slate50, unfocusedTextColor = Slate100)
                    )
                }
                item {
                    OutlinedTextField(
                        value = category,
                        onValueChange = { category = it },
                        label = { Text("Category (e.g. Muscle Gain, Fat Loss)") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Slate50, unfocusedTextColor = Slate100)
                    )
                }
                item {
                    OutlinedTextField(
                        value = duration,
                        onValueChange = { duration = it },
                        label = { Text("Duration (e.g. 12 Weeks)") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Slate50, unfocusedTextColor = Slate100)
                    )
                }
                item {
                    OutlinedTextField(
                        value = description,
                        onValueChange = { description = it },
                        label = { Text("Factual Description") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Slate50, unfocusedTextColor = Slate100)
                    )
                }
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = { beforePicker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(containerColor = Slate800)
                        ) {
                            Text(if (beforeUri.isBlank()) "Pick Before" else "✓ Before Set", fontSize = 11.sp)
                        }

                        Button(
                            onClick = { afterPicker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(containerColor = Slate800)
                        ) {
                            Text(if (afterUri.isBlank()) "Pick After" else "✓ After Set", fontSize = 11.sp)
                        }
                    }
                }
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Checkbox(checked = hasPermission, onCheckedChange = { hasPermission = it })
                        Text("Confirmed client permission to publish", fontSize = 12.sp, color = Slate300)
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (title.isNotBlank()) {
                        onSave(
                            BeforeAfterResult(
                                id = "res_${System.currentTimeMillis()}",
                                title = title,
                                beforeImageUri = beforeUri,
                                afterImageUri = afterUri,
                                category = category,
                                duration = duration,
                                description = description,
                                hasOwnerPermission = hasPermission,
                                isPublished = isPublished
                            )
                        )
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = CyanPrimary)
            ) {
                Text("Save Result")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel", color = Slate400) }
        },
        containerColor = Slate900
    )
}

@Composable
private fun TestimonialsEditor(
    testimonials: List<Testimonial>,
    onSaveTestimonial: (Testimonial) -> Unit,
    onDeleteTestimonial: (String) -> Unit
) {
    var showAddDialog by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Client Testimonials", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Slate100)
                    Text("Publish only approved quotes with consent.", fontSize = 12.sp, color = Slate400)
                }

                Button(
                    onClick = { showAddDialog = true },
                    colors = ButtonDefaults.buttonColors(containerColor = CyanPrimary)
                ) {
                    Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Add Testimonial", fontSize = 12.sp)
                }
            }
        }

        if (testimonials.isEmpty()) {
            item {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Slate900,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "No testimonials registered yet. Use 'Add Testimonial' to record an approved client quote.",
                        color = Slate400,
                        fontSize = 13.sp,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }

        items(testimonials) { item ->
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = Slate900,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(item.clientName, fontWeight = FontWeight.Bold, color = Slate100, fontSize = 13.sp)
                        Text("\"${item.testimonial}\"", color = Slate400, fontSize = 12.sp, maxLines = 2)
                        Text(
                            text = if (item.isApproved) "Status: Approved & Public" else "Status: Pending Approval",
                            color = if (item.isApproved) WhatsAppGreen else Slate500,
                            fontSize = 11.sp
                        )
                    }

                    Row {
                        IconButton(onClick = { onSaveTestimonial(item.copy(isApproved = !item.isApproved)) }) {
                            Icon(
                                imageVector = if (item.isApproved) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                                contentDescription = "Toggle approve",
                                tint = if (item.isApproved) WhatsAppGreen else Slate500
                            )
                        }
                        IconButton(onClick = { onDeleteTestimonial(item.id) }) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red.copy(alpha = 0.8f))
                        }
                    }
                }
            }
        }
    }

    if (showAddDialog) {
        var clientName by remember { mutableStateOf("") }
        var quote by remember { mutableStateOf("") }
        var isApproved by remember { mutableStateOf(true) }

        AlertDialog(
            onDismissRequest = { showAddDialog = false },
            title = { Text("Add Approved Testimonial", fontWeight = FontWeight.Bold, color = Slate50) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = clientName,
                        onValueChange = { clientName = it },
                        label = { Text("Client Name *") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Slate50, unfocusedTextColor = Slate100)
                    )
                    OutlinedTextField(
                        value = quote,
                        onValueChange = { quote = it },
                        label = { Text("Testimonial Text *") },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 3,
                        colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Slate50, unfocusedTextColor = Slate100)
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(checked = isApproved, onCheckedChange = { isApproved = it })
                        Text("Approved for publication", fontSize = 12.sp, color = Slate300)
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (clientName.isNotBlank() && quote.isNotBlank()) {
                            onSaveTestimonial(
                                Testimonial(
                                    id = "test_${System.currentTimeMillis()}",
                                    clientName = clientName,
                                    testimonial = quote,
                                    isApproved = isApproved
                                )
                            )
                            showAddDialog = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = CyanPrimary)
                ) {
                    Text("Save Testimonial")
                }
            },
            dismissButton = {
                TextButton(onClick = { showAddDialog = false }) { Text("Cancel", color = Slate400) }
            },
            containerColor = Slate900
        )
    }
}

@Composable
private fun FaqsEditor(
    faqs: List<FaqItem>,
    onSaveFaq: (FaqItem) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text("FAQ Management", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Slate100)
            Text("Keep answers updated with verified center information.", fontSize = 12.sp, color = Slate400)
        }

        items(faqs) { faq ->
            var answer by remember(faq) { mutableStateOf(faq.answer) }

            Surface(
                shape = RoundedCornerShape(12.dp),
                color = Slate900,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(faq.question, fontWeight = FontWeight.Bold, color = Slate100, fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = answer,
                        onValueChange = { answer = it },
                        label = { Text("Answer") },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 2,
                        colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Slate50, unfocusedTextColor = Slate100)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Button(
                        onClick = { onSaveFaq(faq.copy(answer = answer)) },
                        modifier = Modifier.align(Alignment.End),
                        colors = ButtonDefaults.buttonColors(containerColor = Slate800)
                    ) {
                        Text("Save Answer", fontSize = 11.sp)
                    }
                }
            }
        }
    }
}
