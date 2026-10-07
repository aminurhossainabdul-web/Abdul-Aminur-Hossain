package com.example.ui.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.*
import com.example.data.repository.ContentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.net.URLEncoder

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ContentRepository(application.applicationContext)

    val centerInfo: StateFlow<CenterInfo> = repository.centerInfo
    val services: StateFlow<List<FitnessService>> = repository.services
    val results: StateFlow<List<BeforeAfterResult>> = repository.results
    val whyPoints: StateFlow<List<WhyChooseUsPoint>> = repository.whyPoints
    val testimonials: StateFlow<List<Testimonial>> = repository.testimonials
    val faqs: StateFlow<List<FaqItem>> = repository.faqs

    private val _isOwnerMode = MutableStateFlow(false)
    val isOwnerMode: StateFlow<Boolean> = _isOwnerMode.asStateFlow()

    private val _selectedResult = MutableStateFlow<BeforeAfterResult?>(null)
    val selectedResult: StateFlow<BeforeAfterResult?> = _selectedResult.asStateFlow()

    private val _activeCategory = MutableStateFlow("All")
    val activeCategory: StateFlow<String> = _activeCategory.asStateFlow()

    // Ask Me Something Form State
    var enquiryName = MutableStateFlow("")
        private set
    var enquiryPhone = MutableStateFlow("")
        private set
    var enquiryQuestion = MutableStateFlow("")
        private set
    var enquiryMessage = MutableStateFlow("")
        private set

    var formErrorMessage = MutableStateFlow<String?>(null)
        private set

    fun toggleOwnerMode() {
        _isOwnerMode.value = !_isOwnerMode.value
    }

    fun setSelectedResult(result: BeforeAfterResult?) {
        _selectedResult.value = result
    }

    fun setActiveCategory(category: String) {
        _activeCategory.value = category
    }

    fun onEnquiryNameChange(name: String) {
        enquiryName.value = name
        formErrorMessage.value = null
    }

    fun onEnquiryPhoneChange(phone: String) {
        enquiryPhone.value = phone
        formErrorMessage.value = null
    }

    fun onEnquiryQuestionChange(question: String) {
        enquiryQuestion.value = question
        formErrorMessage.value = null
    }

    fun onEnquiryMessageChange(message: String) {
        enquiryMessage.value = message
    }

    fun submitEnquiry(context: Context) {
        val name = enquiryName.value.trim()
        val phone = enquiryPhone.value.trim()
        val question = enquiryQuestion.value.trim()
        val extra = enquiryMessage.value.trim()

        if (name.isEmpty()) {
            formErrorMessage.value = "Please enter your name"
            return
        }
        if (phone.isEmpty() || phone.length < 6) {
            formErrorMessage.value = "Please enter a valid phone number"
            return
        }
        if (question.isEmpty()) {
            formErrorMessage.value = "Please enter your question"
            return
        }

        val text = buildString {
            append("Hello New Life Fitness Center,\n")
            append("Name: $name\n")
            append("Phone: $phone\n")
            append("Question: $question\n")
            if (extra.isNotEmpty()) {
                append("Details: $extra\n")
            }
        }

        openWhatsApp(context, text)
    }

    fun openWhatsApp(context: Context, customMessage: String? = null) {
        val message = customMessage ?: centerInfo.value.defaultWhatsAppMessage
        try {
            val encoded = URLEncoder.encode(message, "UTF-8")
            val cleanPhone = centerInfo.value.whatsAppNumber.replace("[^0-9]".toRegex(), "")
            val url = "https://wa.me/$cleanPhone?text=$encoded"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Could not open WhatsApp. Please check if installed.", Toast.LENGTH_LONG).show()
        }
    }

    fun openDialer(context: Context) {
        try {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse(centerInfo.value.telUri)).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Could not open telephone dialer.", Toast.LENGTH_SHORT).show()
        }
    }

    fun openInstagram(context: Context) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(centerInfo.value.instagramUrl)).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Could not open Instagram link.", Toast.LENGTH_SHORT).show()
        }
    }

    fun openFacebook(context: Context) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(centerInfo.value.facebookUrl)).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Could not open Facebook link.", Toast.LENGTH_SHORT).show()
        }
    }

    fun openMaps(context: Context) {
        val mapsUrl = centerInfo.value.mapsUrl
        if (mapsUrl.isNotBlank()) {
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(mapsUrl)).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                context.startActivity(intent)
                return
            } catch (e: Exception) {
                // fallback to WhatsApp
            }
        }
        // If awaiting address or maps URL, guide on WhatsApp as per prompt instructions
        openWhatsApp(context, "Hello New Life Fitness Center, could you please share your exact gym location and address?")
    }

    // --- Content Management (CMS) Actions for Owner ---
    fun updateCenterInfo(info: CenterInfo) {
        viewModelScope.launch {
            repository.updateCenterInfo(info)
        }
    }

    fun updateService(service: FitnessService) {
        viewModelScope.launch {
            val current = services.value.toMutableList()
            val index = current.indexOfFirst { it.id == service.id }
            if (index >= 0) {
                current[index] = service
            } else {
                current.add(service)
            }
            repository.updateServices(current)
        }
    }

    fun deleteService(serviceId: String) {
        viewModelScope.launch {
            val updated = services.value.filterNot { it.id == serviceId }
            repository.updateServices(updated)
        }
    }

    fun saveResult(result: BeforeAfterResult) {
        viewModelScope.launch {
            val current = results.value.toMutableList()
            val index = current.indexOfFirst { it.id == result.id }
            if (index >= 0) {
                current[index] = result
            } else {
                current.add(result)
            }
            repository.saveResults(current)
        }
    }

    fun deleteResult(resultId: String) {
        viewModelScope.launch {
            val updated = results.value.filterNot { it.id == resultId }
            repository.saveResults(updated)
        }
    }

    fun toggleWhyPointConfirmed(pointId: String) {
        viewModelScope.launch {
            val current = whyPoints.value.map {
                if (it.id == pointId) it.copy(isConfirmed = !it.isConfirmed) else it
            }
            repository.updateWhyPoints(current)
        }
    }

    fun saveTestimonial(testimonial: Testimonial) {
        viewModelScope.launch {
            val current = testimonials.value.toMutableList()
            val index = current.indexOfFirst { it.id == testimonial.id }
            if (index >= 0) {
                current[index] = testimonial
            } else {
                current.add(testimonial)
            }
            repository.saveTestimonials(current)
        }
    }

    fun deleteTestimonial(id: String) {
        viewModelScope.launch {
            val updated = testimonials.value.filterNot { it.id == id }
            repository.saveTestimonials(updated)
        }
    }

    fun saveFaq(faq: FaqItem) {
        viewModelScope.launch {
            val current = faqs.value.toMutableList()
            val index = current.indexOfFirst { it.id == faq.id }
            if (index >= 0) {
                current[index] = faq
            } else {
                current.add(faq)
            }
            repository.updateFaqs(current)
        }
    }
}
