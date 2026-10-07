package com.example.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.data.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.json.JSONArray
import org.json.JSONObject

class ContentRepository(private val context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("nlfc_content_prefs", Context.MODE_PRIVATE)

    private val _centerInfo = MutableStateFlow(loadCenterInfo())
    val centerInfo: StateFlow<CenterInfo> = _centerInfo.asStateFlow()

    private val _services = MutableStateFlow(loadServices())
    val services: StateFlow<List<FitnessService>> = _services.asStateFlow()

    private val _results = MutableStateFlow(loadResults())
    val results: StateFlow<List<BeforeAfterResult>> = _results.asStateFlow()

    private val _whyPoints = MutableStateFlow(loadWhyPoints())
    val whyPoints: StateFlow<List<WhyChooseUsPoint>> = _whyPoints.asStateFlow()

    private val _testimonials = MutableStateFlow(loadTestimonials())
    val testimonials: StateFlow<List<Testimonial>> = _testimonials.asStateFlow()

    private val _faqs = MutableStateFlow(loadFaqs())
    val faqs: StateFlow<List<FaqItem>> = _faqs.asStateFlow()

    // --- Center Info ---
    private fun loadCenterInfo(): CenterInfo {
        val json = prefs.getString("center_info", null) ?: return CenterInfo()
        return try {
            val obj = JSONObject(json)
            CenterInfo(
                name = obj.optString("name", "NEW LIFE FITNESS CENTER"),
                tagline = obj.optString("tagline", "Transform Your Health. Transform Your Life."),
                phone = obj.optString("phone", "+91 9933521448"),
                telUri = obj.optString("telUri", "tel:+919933521448"),
                instagramHandle = obj.optString("instagramHandle", "@abdulmajid786indian"),
                instagramUrl = obj.optString("instagramUrl", "https://www.instagram.com/abdulmajid786indian?stkn=ZjZraDZ0amJzZTI1"),
                facebookLabel = obj.optString("facebookLabel", "New Life Fitness Center Facebook Page"),
                facebookUrl = obj.optString("facebookUrl", "https://www.facebook.com/share/1HptEHRwSr/"),
                whatsAppNumber = obj.optString("whatsAppNumber", "+91 9933521448"),
                defaultWhatsAppMessage = obj.optString("defaultWhatsAppMessage", "Hello New Life Fitness Center, I would like to know more about your services."),
                address = obj.optString("address", "Awaiting owner-provided address"),
                openingHours = obj.optString("openingHours", "Awaiting owner-provided opening hours"),
                mapsUrl = obj.optString("mapsUrl", ""),
                heroIntro = obj.optString("heroIntro", "Welcome to New Life Fitness Center. Connect with us to learn more about our center and services."),
                aboutTheCenter = obj.optString("aboutTheCenter", "Welcome to New Life Fitness Center. We provide a focused, energetic training facility helping individuals achieve their personal health and fitness goals."),
                aboutApproach = obj.optString("aboutApproach", "Structured personal attention, guided workout routines, and dedicated encouragement for every fitness level."),
                aboutPhilosophy = obj.optString("aboutPhilosophy", "Sustainable progress through consistent training, proper form, and holistic wellness."),
                aboutExperience = obj.optString("aboutExperience", "Hands-on fitness guidance tailored to each individual member's pace and abilities."),
                aboutGoal = obj.optString("aboutGoal", "Empowering you to feel stronger, healthier, and more confident every day.")
            )
        } catch (e: Exception) {
            CenterInfo()
        }
    }

    fun updateCenterInfo(info: CenterInfo) {
        val obj = JSONObject().apply {
            put("name", info.name)
            put("tagline", info.tagline)
            put("phone", info.phone)
            put("telUri", info.telUri)
            put("instagramHandle", info.instagramHandle)
            put("instagramUrl", info.instagramUrl)
            put("facebookLabel", info.facebookLabel)
            put("facebookUrl", info.facebookUrl)
            put("whatsAppNumber", info.whatsAppNumber)
            put("defaultWhatsAppMessage", info.defaultWhatsAppMessage)
            put("address", info.address)
            put("openingHours", info.openingHours)
            put("mapsUrl", info.mapsUrl)
            put("heroIntro", info.heroIntro)
            put("aboutTheCenter", info.aboutTheCenter)
            put("aboutApproach", info.aboutApproach)
            put("aboutPhilosophy", info.aboutPhilosophy)
            put("aboutExperience", info.aboutExperience)
            put("aboutGoal", info.aboutGoal)
        }
        prefs.edit().putString("center_info", obj.toString()).apply()
        _centerInfo.value = info
    }

    // --- Services ---
    private fun loadServices(): List<FitnessService> {
        val json = prefs.getString("services", null)
        if (json != null) {
            try {
                val array = JSONArray(json)
                val list = mutableListOf<FitnessService>()
                for (i in 0 until array.length()) {
                    val obj = array.getJSONObject(i)
                    list.add(
                        FitnessService(
                            id = obj.getString("id"),
                            name = obj.getString("name"),
                            iconKey = obj.optString("iconKey", "fitness"),
                            shortDescription = obj.getString("shortDescription"),
                            details = obj.optString("details", ""),
                            isPublished = obj.optBoolean("isPublished", false)
                        )
                    )
                }
                return list
            } catch (e: Exception) {
                // fallback
            }
        }
        return defaultServices()
    }

    private fun defaultServices(): List<FitnessService> {
        return listOf(
            FitnessService(
                id = "srv_1",
                name = "Weight Training & Muscle Building",
                iconKey = "fitness",
                shortDescription = "Progressive resistance training with free weights and targeted machinery.",
                details = "Coached form correction, progressive overload schedules, and dedicated workout splits.",
                isPublished = true
            ),
            FitnessService(
                id = "srv_2",
                name = "Weight Loss & Conditioning",
                iconKey = "burn",
                shortDescription = "High-energy endurance and calorie-burning workout regimens.",
                details = "Structured circuits combining cardio, functional movement, and stamina training.",
                isPublished = true
            ),
            FitnessService(
                id = "srv_3",
                name = "Personal Training & Guidance",
                iconKey = "person",
                shortDescription = "One-on-one attention focused on your individual fitness targets.",
                details = "Dedicated attention ensuring safety, motivation, and disciplined consistency.",
                isPublished = true
            ),
            FitnessService(
                id = "srv_4",
                name = "Strength & Endurance Training",
                iconKey = "bolt",
                shortDescription = "Core strength conditioning to increase overall physical capability.",
                details = "Functional lifts, core stability, and endurance drills tailored to your baseline.",
                isPublished = true
            ),
            FitnessService(
                id = "srv_5",
                name = "Cardio & General Fitness",
                iconKey = "run",
                shortDescription = "Cardiovascular health routines designed for everyday stamina and energy.",
                details = "Heart-healthy aerobics, treadmill, cycling, and agility training routines.",
                isPublished = true
            ),
            FitnessService(
                id = "srv_6",
                name = "Diet & Workout Planning Guidance",
                iconKey = "nutrition",
                shortDescription = "Practical habit advice to support your workout recovery and energy.",
                details = "Sustainable nutritional habits aligned with your workout goals.",
                isPublished = true
            )
        )
    }

    fun updateServices(services: List<FitnessService>) {
        val array = JSONArray()
        for (item in services) {
            val obj = JSONObject().apply {
                put("id", item.id)
                put("name", item.name)
                put("iconKey", item.iconKey)
                put("shortDescription", item.shortDescription)
                put("details", item.details)
                put("isPublished", item.isPublished)
            }
            array.put(obj)
        }
        prefs.edit().putString("services", array.toString()).apply()
        _services.value = services
    }

    // --- Before & After Results ---
    private fun loadResults(): List<BeforeAfterResult> {
        val json = prefs.getString("results", null)
        if (json != null) {
            try {
                val array = JSONArray(json)
                val list = mutableListOf<BeforeAfterResult>()
                for (i in 0 until array.length()) {
                    val obj = array.getJSONObject(i)
                    list.add(
                        BeforeAfterResult(
                            id = obj.getString("id"),
                            title = obj.getString("title"),
                            beforeImageUri = obj.optString("beforeImageUri", ""),
                            afterImageUri = obj.optString("afterImageUri", ""),
                            category = obj.optString("category", "General Fitness"),
                            duration = obj.optString("duration", ""),
                            description = obj.optString("description", ""),
                            hasOwnerPermission = obj.optBoolean("hasOwnerPermission", true),
                            isPublished = obj.optBoolean("isPublished", false)
                        )
                    )
                }
                return list
            } catch (e: Exception) {
                // fallback
            }
        }
        return emptyList()
    }

    fun saveResults(results: List<BeforeAfterResult>) {
        val array = JSONArray()
        for (item in results) {
            val obj = JSONObject().apply {
                put("id", item.id)
                put("title", item.title)
                put("beforeImageUri", item.beforeImageUri)
                put("afterImageUri", item.afterImageUri)
                put("category", item.category)
                put("duration", item.duration)
                put("description", item.description)
                put("hasOwnerPermission", item.hasOwnerPermission)
                put("isPublished", item.isPublished)
            }
            array.put(obj)
        }
        prefs.edit().putString("results", array.toString()).apply()
        _results.value = results
    }

    // --- Why Choose Us ---
    private fun loadWhyPoints(): List<WhyChooseUsPoint> {
        val json = prefs.getString("why_points", null)
        if (json != null) {
            try {
                val array = JSONArray(json)
                val list = mutableListOf<WhyChooseUsPoint>()
                for (i in 0 until array.length()) {
                    val obj = array.getJSONObject(i)
                    list.add(
                        WhyChooseUsPoint(
                            id = obj.getString("id"),
                            title = obj.getString("title"),
                            description = obj.getString("description"),
                            isConfirmed = obj.optBoolean("isConfirmed", false),
                            isPublished = obj.optBoolean("isPublished", true)
                        )
                    )
                }
                return list
            } catch (e: Exception) {
                // fallback
            }
        }
        return listOf(
            WhyChooseUsPoint(
                id = "why_1",
                title = "Personalized Attention",
                description = "Direct guidance from passionate fitness trainers who focus on your unique needs.",
                isConfirmed = true,
                isPublished = true
            ),
            WhyChooseUsPoint(
                id = "why_2",
                title = "Professional Guidance",
                description = "Hands-on instruction on proper exercise form, lifting techniques, and workout safety.",
                isConfirmed = true,
                isPublished = true
            ),
            WhyChooseUsPoint(
                id = "why_3",
                title = "Supportive Environment",
                description = "A warm, respectful, and motivating fitness atmosphere for both beginners and athletes.",
                isConfirmed = true,
                isPublished = true
            ),
            WhyChooseUsPoint(
                id = "why_4",
                title = "Health-Focused Approach",
                description = "Prioritizing injury prevention, sustainable stamina, and lasting physical well-being.",
                isConfirmed = true,
                isPublished = true
            ),
            WhyChooseUsPoint(
                id = "why_5",
                title = "Convenient Communication",
                description = "Direct, prompt updates and appointment scheduling through WhatsApp and phone.",
                isConfirmed = true,
                isPublished = true
            ),
            WhyChooseUsPoint(
                id = "why_6",
                title = "Client-Focused Service",
                description = "Your personal health transformation and comfort remain our top priority.",
                isConfirmed = true,
                isPublished = true
            )
        )
    }

    fun updateWhyPoints(points: List<WhyChooseUsPoint>) {
        val array = JSONArray()
        for (item in points) {
            val obj = JSONObject().apply {
                put("id", item.id)
                put("title", item.title)
                put("description", item.description)
                put("isConfirmed", item.isConfirmed)
                put("isPublished", item.isPublished)
            }
            array.put(obj)
        }
        prefs.edit().putString("why_points", array.toString()).apply()
        _whyPoints.value = points
    }

    // --- Testimonials ---
    private fun loadTestimonials(): List<Testimonial> {
        val json = prefs.getString("testimonials", null)
        if (json != null) {
            try {
                val array = JSONArray(json)
                val list = mutableListOf<Testimonial>()
                for (i in 0 until array.length()) {
                    val obj = array.getJSONObject(i)
                    list.add(
                        Testimonial(
                            id = obj.getString("id"),
                            clientName = obj.getString("clientName"),
                            testimonial = obj.getString("testimonial"),
                            rating = obj.optInt("rating", 5),
                            clientPhotoUri = obj.optString("clientPhotoUri", ""),
                            isApproved = obj.optBoolean("isApproved", false)
                        )
                    )
                }
                return list
            } catch (e: Exception) {
                // fallback
            }
        }
        return emptyList()
    }

    fun saveTestimonials(list: List<Testimonial>) {
        val array = JSONArray()
        for (item in list) {
            val obj = JSONObject().apply {
                put("id", item.id)
                put("clientName", item.clientName)
                put("testimonial", item.testimonial)
                put("rating", item.rating)
                put("clientPhotoUri", item.clientPhotoUri)
                put("isApproved", item.isApproved)
            }
            array.put(obj)
        }
        prefs.edit().putString("testimonials", array.toString()).apply()
        _testimonials.value = list
    }

    // --- FAQs ---
    private fun loadFaqs(): List<FaqItem> {
        val json = prefs.getString("faqs", null)
        if (json != null) {
            try {
                val array = JSONArray(json)
                val list = mutableListOf<FaqItem>()
                for (i in 0 until array.length()) {
                    val obj = array.getJSONObject(i)
                    list.add(
                        FaqItem(
                            id = obj.getString("id"),
                            question = obj.getString("question"),
                            answer = obj.getString("answer"),
                            isPublished = obj.optBoolean("isPublished", true)
                        )
                    )
                }
                return list
            } catch (e: Exception) {
                // fallback
            }
        }
        return listOf(
            FaqItem(
                id = "faq_1",
                question = "What services does New Life Fitness Center provide?",
                answer = "We provide weight training, muscle building, weight loss & conditioning, personal guidance, and cardiovascular fitness routines. Connect with us on WhatsApp for our complete current routine and membership options.",
                isPublished = true
            ),
            FaqItem(
                id = "faq_2",
                question = "How can I contact the center?",
                answer = "You can reach us directly by calling +91 9933521448 or messaging us on WhatsApp (+91 9933521448). You can also follow and message us on our official Instagram (@abdulmajid786indian) and Facebook page.",
                isPublished = true
            ),
            FaqItem(
                id = "faq_3",
                question = "How can I connect through WhatsApp?",
                answer = "Simply tap the 'WhatsApp Us' button anywhere on our app or message +91 9933521448 directly. A prefilled chat will open where you can discuss training, schedules, or queries.",
                isPublished = true
            ),
            FaqItem(
                id = "faq_4",
                question = "Where is the center located?",
                answer = "Please contact us directly on WhatsApp (+91 9933521448) or call us for our exact physical address, landmark directions, and gym visit schedule.",
                isPublished = true
            ),
            FaqItem(
                id = "faq_5",
                question = "What are the opening hours?",
                answer = "Please enquire via WhatsApp or phone (+91 9933521448) to confirm today's opening times, morning/evening batches, and trainer availability.",
                isPublished = true
            ),
            FaqItem(
                id = "faq_6",
                question = "How can I enquire about the services?",
                answer = "You can use our 'Ask Me Something' enquiry form (which opens WhatsApp with your prefilled question), tap any 'Enquire on WhatsApp' button, or call +91 9933521448 directly.",
                isPublished = true
            )
        )
    }

    fun updateFaqs(faqs: List<FaqItem>) {
        val array = JSONArray()
        for (item in faqs) {
            val obj = JSONObject().apply {
                put("id", item.id)
                put("question", item.question)
                put("answer", item.answer)
                put("isPublished", item.isPublished)
            }
            array.put(obj)
        }
        prefs.edit().putString("faqs", array.toString()).apply()
        _faqs.value = faqs
    }
}
