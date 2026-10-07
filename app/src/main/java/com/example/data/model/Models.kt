package com.example.data.model

data class CenterInfo(
    val name: String = "NEW LIFE FITNESS CENTER",
    val tagline: String = "Transform Your Health. Transform Your Life.",
    val phone: String = "+91 9933521448",
    val telUri: String = "tel:+919933521448",
    val instagramHandle: String = "@abdulmajid786indian",
    val instagramUrl: String = "https://www.instagram.com/abdulmajid786indian?stkn=ZjZraDZ0amJzZTI1",
    val facebookLabel: String = "New Life Fitness Center Facebook Page",
    val facebookUrl: String = "https://www.facebook.com/share/1HptEHRwSr/",
    val whatsAppNumber: String = "+91 9933521448",
    val defaultWhatsAppMessage: String = "Hello New Life Fitness Center, I would like to know more about your services.",
    val address: String = "Awaiting owner-provided address",
    val openingHours: String = "Awaiting owner-provided opening hours",
    val mapsUrl: String = "",
    val heroIntro: String = "Welcome to New Life Fitness Center. Connect with us to learn more about our center and services.",
    val aboutTheCenter: String = "Welcome to New Life Fitness Center. We provide a focused, energetic training facility helping individuals achieve their personal health and fitness goals.",
    val aboutApproach: String = "Structured personal attention, guided workout routines, and dedicated encouragement for every fitness level.",
    val aboutPhilosophy: String = "Sustainable progress through consistent training, proper form, and holistic wellness.",
    val aboutExperience: String = "Hands-on fitness guidance tailored to each individual member's pace and abilities.",
    val aboutGoal: String = "Empowering you to feel stronger, healthier, and more confident every day.",
    val resultsDisclaimer: String = "Results may vary from person to person. Images are shown for informational and promotional purposes only and do not guarantee similar results."
)

data class FitnessService(
    val id: String,
    val name: String,
    val iconKey: String = "fitness",
    val shortDescription: String,
    val details: String = "",
    val isPublished: Boolean = false
)

data class BeforeAfterResult(
    val id: String,
    val title: String,
    val beforeImageUri: String = "",
    val afterImageUri: String = "",
    val category: String = "General Fitness",
    val duration: String = "",
    val description: String = "",
    val hasOwnerPermission: Boolean = true,
    val isPublished: Boolean = false
)

data class WhyChooseUsPoint(
    val id: String,
    val title: String,
    val description: String,
    val isConfirmed: Boolean = false,
    val isPublished: Boolean = true
)

data class Testimonial(
    val id: String,
    val clientName: String,
    val testimonial: String,
    val rating: Int = 5,
    val clientPhotoUri: String = "",
    val isApproved: Boolean = false
)

data class FaqItem(
    val id: String,
    val question: String,
    val answer: String,
    val isPublished: Boolean = true
)

enum class NavSection(val label: String, val icon: String) {
    HOME("Home", "home"),
    ABOUT("About Us", "info"),
    SERVICES("Services", "fitness_center"),
    RESULTS("Results", "star"),
    WHY_US("Why Choose Us", "check_circle"),
    TESTIMONIALS("Testimonials", "rate_review"),
    ASK("Ask Us", "help"),
    CONNECT("Connect", "share"),
    CONTACT("Contact", "contact_phone"),
    FAQ("FAQ", "question_answer")
}
