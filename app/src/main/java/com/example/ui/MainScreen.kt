package com.example.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.model.NavSection
import com.example.ui.components.*
import com.example.ui.theme.Slate950
import com.example.ui.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    val centerInfo by viewModel.centerInfo.collectAsStateWithLifecycle()
    val services by viewModel.services.collectAsStateWithLifecycle()
    val results by viewModel.results.collectAsStateWithLifecycle()
    val whyPoints by viewModel.whyPoints.collectAsStateWithLifecycle()
    val testimonials by viewModel.testimonials.collectAsStateWithLifecycle()
    val faqs by viewModel.faqs.collectAsStateWithLifecycle()

    val isOwnerMode by viewModel.isOwnerMode.collectAsStateWithLifecycle()
    val selectedResult by viewModel.selectedResult.collectAsStateWithLifecycle()

    val enquiryName by viewModel.enquiryName.collectAsStateWithLifecycle()
    val enquiryPhone by viewModel.enquiryPhone.collectAsStateWithLifecycle()
    val enquiryQuestion by viewModel.enquiryQuestion.collectAsStateWithLifecycle()
    val enquiryMessage by viewModel.enquiryMessage.collectAsStateWithLifecycle()
    val formErrorMessage by viewModel.formErrorMessage.collectAsStateWithLifecycle()

    // Function to scroll directly to corresponding section
    val scrollToSection: (NavSection) -> Unit = { section ->
        coroutineScope.launch {
            val targetIndex = when (section) {
                NavSection.HOME -> 0
                NavSection.ABOUT -> 1
                NavSection.SERVICES -> 2
                NavSection.RESULTS -> 3
                NavSection.WHY_US -> 4
                NavSection.TESTIMONIALS -> 5
                NavSection.ASK -> 6
                NavSection.CONNECT -> 7
                NavSection.CONTACT -> 8
                NavSection.FAQ -> 9
            }
            listState.animateScrollToItem(targetIndex)
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Slate950,
        topBar = {
            HeaderBar(
                onWhatsAppClick = { viewModel.openWhatsApp(context) },
                onOwnerToggle = { viewModel.toggleOwnerMode() },
                isOwnerMode = isOwnerMode,
                onNavSelect = scrollToSection
            )
        },
        floatingActionButton = {
            FloatingActionMenu(
                onWhatsAppClick = { viewModel.openWhatsApp(context) },
                onCallClick = { viewModel.openDialer(context) },
                onInstagramClick = { viewModel.openInstagram(context) }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.TopCenter
        ) {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 1000.dp),
                contentPadding = PaddingValues(bottom = 32.dp)
            ) {
                // 0. Hero Section
            item {
                HeroSection(
                    centerInfo = centerInfo,
                    onConnectClick = { scrollToSection(NavSection.CONNECT) },
                    onWhatsAppClick = { viewModel.openWhatsApp(context) },
                    onCallClick = { viewModel.openDialer(context) }
                )
            }

            // 1. About Us Section
            item {
                AboutSection(
                    centerInfo = centerInfo,
                    onLearnMoreClick = {
                        viewModel.openWhatsApp(
                            context,
                            "Hello New Life Fitness Center, I would like to learn more about your center and fitness philosophy."
                        )
                    }
                )
            }

            // 2. Services Section (6 Slots)
            item {
                ServicesSection(
                    services = services,
                    onEnquireService = { service ->
                        viewModel.openWhatsApp(
                            context,
                            "Hello New Life Fitness Center, I would like to enquire about your service: ${service.name}."
                        )
                    }
                )
            }

            // 3. Real Results (Before & After)
            item {
                ResultsSection(
                    results = results,
                    onSelectResult = { item -> viewModel.setSelectedResult(item) },
                    onViewMoreClick = {
                        val first = results.firstOrNull { it.isPublished }
                        viewModel.setSelectedResult(first)
                    },
                    onWhatsAppClick = {
                        viewModel.openWhatsApp(
                            context,
                            "Hello New Life Fitness Center, I would like to enquire about verified client results and transformation routines."
                        )
                    },
                    disclaimerText = centerInfo.resultsDisclaimer
                )
            }

            // 4. Why Choose Us Section
            item {
                WhyChooseUsSection(points = whyPoints)
            }

            // 5. Testimonials Section
            item {
                TestimonialsSection(
                    testimonials = testimonials,
                    onWhatsAppClick = { viewModel.openWhatsApp(context) }
                )
            }

            // 6. Ask Me Something Section
            item {
                AskMeSomethingSection(
                    name = enquiryName,
                    onNameChange = { viewModel.onEnquiryNameChange(it) },
                    phone = enquiryPhone,
                    onPhoneChange = { viewModel.onEnquiryPhoneChange(it) },
                    question = enquiryQuestion,
                    onQuestionChange = { viewModel.onEnquiryQuestionChange(it) },
                    message = enquiryMessage,
                    onMessageChange = { viewModel.onEnquiryMessageChange(it) },
                    errorMessage = formErrorMessage,
                    onSendQuestionClick = { viewModel.submitEnquiry(context) },
                    onDirectWhatsAppClick = { viewModel.openWhatsApp(context) }
                )
            }

            // 7. Connect With Us Section
            item {
                ConnectWithUsSection(
                    centerInfo = centerInfo,
                    onInstagramClick = { viewModel.openInstagram(context) },
                    onFacebookClick = { viewModel.openFacebook(context) },
                    onWhatsAppClick = { viewModel.openWhatsApp(context) }
                )
            }

            // 8. Contact & Directions Section
            item {
                ContactSection(
                    centerInfo = centerInfo,
                    onCallClick = { viewModel.openDialer(context) },
                    onWhatsAppClick = { viewModel.openWhatsApp(context) },
                    onInstagramClick = { viewModel.openInstagram(context) },
                    onFacebookClick = { viewModel.openFacebook(context) },
                    onDirectionsClick = { viewModel.openMaps(context) }
                )
            }

            // 9. FAQ Section
            item {
                FaqSection(
                    faqs = faqs,
                    onAskOnWhatsApp = { viewModel.openWhatsApp(context) }
                )
            }

            // 10. Final Call To Action
            item {
                FinalCtaSection(
                    onConnectClick = { scrollToSection(NavSection.CONNECT) },
                    onWhatsAppClick = { viewModel.openWhatsApp(context) },
                    onCallClick = { viewModel.openDialer(context) }
                )
            }

            // 11. Footer Section
            item {
                FooterSection(
                    centerInfo = centerInfo,
                    onNavSelect = scrollToSection,
                    onInstagramClick = { viewModel.openInstagram(context) },
                    onFacebookClick = { viewModel.openFacebook(context) },
                    onWhatsAppClick = { viewModel.openWhatsApp(context) }
                )
            }
        }
    }
}

    // Results Gallery Modal / Zoom Inspector
    selectedResult?.let { item ->
        ResultsGalleryModal(
            results = results,
            initialResult = item,
            onDismiss = { viewModel.setSelectedResult(null) },
            disclaimerText = centerInfo.resultsDisclaimer
        )
    }

    // Owner CMS Management Dialog
    if (isOwnerMode) {
        OwnerCmsDialog(
            centerInfo = centerInfo,
            services = services,
            results = results,
            whyPoints = whyPoints,
            testimonials = testimonials,
            faqs = faqs,
            onUpdateCenterInfo = { viewModel.updateCenterInfo(it) },
            onUpdateService = { viewModel.updateService(it) },
            onSaveResult = { viewModel.saveResult(it) },
            onDeleteResult = { viewModel.deleteResult(it) },
            onToggleWhyPoint = { viewModel.toggleWhyPointConfirmed(it) },
            onSaveTestimonial = { viewModel.saveTestimonial(it) },
            onDeleteTestimonial = { viewModel.deleteTestimonial(it) },
            onSaveFaq = { viewModel.saveFaq(it) },
            onDismiss = { viewModel.toggleOwnerMode() }
        )
    }
}
